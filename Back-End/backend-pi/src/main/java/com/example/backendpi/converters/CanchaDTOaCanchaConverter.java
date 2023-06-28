package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.dto.CanchaDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
@AllArgsConstructor

@Component
public class CanchaDTOaCanchaConverter implements Converter<CanchaDTO, Cancha> {

    private final ImagesDTOToImagesConverter imagesDTOToImagesConverter;
    @Override
    public Cancha convert(CanchaDTO source) {
        Cancha cancha = new Cancha();
        cancha.setId(source.getId());
        cancha.setDomicilio(source.getDomicilio());
        cancha.setNombre(source.getNombre());
        cancha.setCategoria(source.getCategoria());
        cancha.setPrecioxhora(source.getPrecio());

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
//        cancha.setHoraApertura(LocalDateTime.parse(source.getHoraApertura().format(formatter)));
//        cancha.setHoraCierre(LocalDateTime.parse(source.getHoraCierre().format(formatter)));
        cancha.setHoraCierre(source.getHoraCierre());
        cancha.setHoraApertura(source.getHoraApertura());
        cancha.setTelefono(source.getTelefono());
        cancha.setCriteriosList(source.getCriteriosList());
        cancha.setPromedioPuntuacion(source.getPromedio());
        cancha.setServicioList(source.getServicioList());
        cancha.setDescripcion(source.getDescripcion());
        return cancha;
    }
}

