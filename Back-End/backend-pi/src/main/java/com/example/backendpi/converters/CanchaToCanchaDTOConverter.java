package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Images;
import com.example.backendpi.domain.Valoracion;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.ImagesDTO;
import com.example.backendpi.dto.ValoracionDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CanchaToCanchaDTOConverter implements Converter<Cancha, CanchaDTO> {

    private final ValoracionToValoracionDTOConverter valoracionToValoracionDTOConverter;

    private final ImagesToImagesDTOConverter imagesToImagesDTOConverter;
    @Override
    public CanchaDTO convert(Cancha source) {
        CanchaDTO  canchaDTO = new CanchaDTO();
        Double promedio = 0.0;
        List<ImagesDTO> imagesDTOList = new ArrayList<>();
        List<ValoracionDTO> valoracionDTOS = new ArrayList<>();
        canchaDTO.setId(source.getId());
        canchaDTO.setCategoria(source.getCategoria());
        canchaDTO.setPrecio(source.getPrecioxhora());
        canchaDTO.setDomicilio(source.getDomicilio());
        canchaDTO.setNombre(source.getNombre());
        canchaDTO.setTelefono(source.getTelefono());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        canchaDTO.setHoraApertura(LocalDateTime.parse(source.getHoraApertura().format(formatter)));
        canchaDTO.setHoraCierre(LocalDateTime.parse(source.getHoraCierre().format(formatter)));

        canchaDTO.setCriteriosList(source.getCriteriosList());
        canchaDTO.setServicioList(source.getServicioList());
        canchaDTO.setDescripcion(source.getDescripcion());
        for (Images image: source.getImgList()){
            imagesDTOList.add(imagesToImagesDTOConverter.convert(image));
        }
        canchaDTO.setImagesDTOSList(imagesDTOList);
        for (Valoracion valoracion : source.getValoracionList() ) {
            valoracionDTOS.add(valoracionToValoracionDTOConverter.convert(valoracion));
            promedio = promedio + valoracion.getPuntuacion();
        }
        canchaDTO.setValoracionList(valoracionDTOS);
        canchaDTO.setPromedio(Math.floor(promedio/valoracionDTOS.size()));
        return canchaDTO;
    }
}
