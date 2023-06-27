package com.example.backendpi.converters;

import com.example.backendpi.domain.Images;
import com.example.backendpi.dto.ImagesDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImagesToImagesDTOConverter implements Converter<Images, ImagesDTO> {

    @Override
    public ImagesDTO convert(Images source) {
        ImagesDTO imagesDTO = new ImagesDTO();
        imagesDTO.setId(source.getId());
        imagesDTO.setUrl(source.getUrl());
//        imagesDTO.setCancha_id(source.getCancha().getId());
//        imagesDTO.setCategoria_id(source.getCategoria().getId());
        return imagesDTO;
    }
}
