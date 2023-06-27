package com.example.backendpi.converters;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CategoriaDTO;
import com.example.backendpi.dto.ImagesDTO;
import com.example.backendpi.repository.ImagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CategoriaToCategoriaDTOConverter implements Converter<Categoria, CategoriaDTO> {

    private final ImagesToImagesDTOConverter imagesToImagesDTOConverter;
private  final ImagesRepository imagesRepository;
    @Override
    public CategoriaDTO convert(Categoria source) {

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setImages(source.getImages());
//        categoriaDTO.setImagen_id(source.getImages().getId());
        categoriaDTO.setId(source.getId());
        categoriaDTO.setNombre(source.getNombre());
        return categoriaDTO;
    }
}
