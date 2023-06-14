package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.dto.CanchaDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class CanchaDTOaCanchaConverter implements Converter<CanchaDTO, Cancha> {
    @Override
    public Cancha convert(CanchaDTO source) {
        Cancha cancha = new Cancha();
        cancha.setId(source.getId());
        cancha.setDomicilio(source.getDomicilio());
        cancha.setNombre(source.getNombre());
        cancha.setCategoria(source.getCategoria());
        cancha.setPrecioxhora(source.getPrecio());
        cancha.setHoraApertura(source.getHoraApertura());
        cancha.setHoraCierre(source.getHoraCierre());
        cancha.setTelefono(source.getTelefono());
        cancha.setCriteriosList(source.getCriteriosList());
        cancha.setPromedioPuntuacion(source.getPromedio());
        return cancha;
    }
}

