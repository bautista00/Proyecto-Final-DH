package com.example.backendpi.service;

import com.example.backendpi.converters.CategoriaToCategoriaDTOConverter;
import com.example.backendpi.converters.ImagesToImagesDTOConverter;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.domain.Images;
import com.example.backendpi.dto.CategoriaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.repository.CategoriaRepository;
import com.example.backendpi.repository.ImagesRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;
    private final AwsS3Service awsS3Service;

    private final ImagesRepository imagesRepository;

    private final CategoriaToCategoriaDTOConverter categoriaToCategoriaDTOConverter;


    @Override
    public Categoria agregarCategoria(Categoria categoria, List<MultipartFile> files) throws Exception {
        if (categoriaRepository.findByNombre(categoria.getNombre()) == null) {
            Categoria categoriaGuardada = categoriaRepository.save(categoria);

            Images images = new Images();
            images.setCategoria(categoriaGuardada);
            images.setUrl(awsS3Service.generateImageUrls(awsS3Service.uploadFiles(files)));
            imagesRepository.save(images);

            return categoriaGuardada;
        }
        throw new EntityExistsException("La categoria ya existe");
    }

    @Override
    public void eliminarCategoria(Long id) throws ResourceNotFoundException {
       Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            categoriaRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("No se encontro la categoria con id "+ id);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() throws ResourceNotFoundException {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        if (categoriaList.size()>0){
            for (Categoria categoria : categoriaList) {
                categoriaDTOS.add(categoriaToCategoriaDTOConverter.convert(categoria));
            }
            return categoriaDTOS;
        }
        throw new ResourceNotFoundException("La listas de categoria esta vacia");
    }
}
