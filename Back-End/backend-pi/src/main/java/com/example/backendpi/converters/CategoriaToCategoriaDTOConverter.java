package com.example.backendpi.converters;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CategoriaDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CategoriaToCategoriaDTOConverter implements Converter<Categoria, CategoriaDTO> {

    private final ImagesToImagesDTOConverter imagesToImagesDTOConverter;

    @Override
    public CategoriaDTO convert(Categoria source) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
//        categoriaDTO.setImagesDTO(imagesToImagesDTOConverter.convert(source.getImages())); no descomentar antes de agg los cat con sus resp img
        categoriaDTO.setId(source.getId());
        categoriaDTO.setNombre(source.getNombre());
        return categoriaDTO;
    }
}
