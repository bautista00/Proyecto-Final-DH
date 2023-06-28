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
        String nombreCategoria = categoria.getNombre();

        if (categoriaRepository.findByNombre(nombreCategoria) != null) {
            throw new EntityExistsException("La categoría ya existe");
        }

        List<String> imageUrls = awsS3Service.generateImageUrls(awsS3Service.uploadFiles(files));
        Images images = new Images();
        images.setUrl(imageUrls);

        Images imagesGuardada = imagesRepository.save(images); // Guardar la imagen en la base de datos

        categoria.setImages(imagesGuardada); // Establecer la imagen guardada como la imagen asociada a la categoría
        Categoria categoriaGuardada = categoriaRepository.save(categoria); // Guardar la categoría en la base de datos

        return categoriaGuardada;
    }



    @Override
    public void eliminarCategoria(Long id) throws ResourceNotFoundException {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            Images images = categoria.getImages();
            if (images != null) {
                categoria.setImages(null);
                categoriaRepository.save(categoria);
                imagesRepository.delete(images);
            }
            categoriaRepository.delete(categoria);
        } else {
            throw new ResourceNotFoundException("No se encontró la categoría con id " + id);
        }
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
