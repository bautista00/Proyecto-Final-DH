package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Images;
import com.example.backendpi.dto.ImagesDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImagesDTOToImagesConverter implements Converter<ImagesDTO, Images> {
    @Override
    public Images convert(ImagesDTO source) {
        Images images = new Images();
        Cancha cancha = new Cancha();
        images.setId(source.getId());
        images.setUrl(source.getUrl());
        cancha.setId(source.getCancha_id());
        images.setCancha(cancha);
        return images;
    }
}
