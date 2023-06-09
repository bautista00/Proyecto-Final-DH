package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Images;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.ImagesDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CanchaToCanchaDTOConverter implements Converter<Cancha, CanchaDTO> {

    private final ImagesToImagesDTOConverter imagesToImagesDTOConverter;
    @Override
    public CanchaDTO convert(Cancha source) {
        CanchaDTO  canchaDTO = new CanchaDTO();
        canchaDTO.setId(source.getId());
        canchaDTO.setCategoria(source.getCategoria());
        canchaDTO.setPrecio(source.getPrecioxhora());
        canchaDTO.setDomicilio(source.getDomicilio());
        canchaDTO.setNombre(source.getNombre());
        canchaDTO.setTelefono(source.getTelefono());
        canchaDTO.setHoraApertura(source.getHoraApertura());
        canchaDTO.setHoraCierre(source.getHoraCierre());
        canchaDTO.setCriteriosSet(source.getCriteriosSet());
        List<ImagesDTO> imagesDTOList = new ArrayList<>();
        for (Images image: source.getImgList()){
            imagesDTOList.add(imagesToImagesDTOConverter.convert(image));
        }
        canchaDTO.setImagesDTOSList(imagesDTOList);
        return canchaDTO;
    }
}
