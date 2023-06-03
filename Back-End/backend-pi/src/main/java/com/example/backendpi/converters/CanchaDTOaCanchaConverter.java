package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.dto.CanchaDTO;
import org.springframework.core.convert.converter.Converter;

public class CanchaDTOaCanchaConverter implements Converter<CanchaDTO, Cancha> {
    @Override
    public Cancha convert(CanchaDTO source) {
        Cancha cancha = new Cancha();
        cancha.setDomicilio(source.getDomicilio());
        cancha.setNombre(source.getNombre());
        cancha.setDeporte(source.getDeporte());
        cancha.setPrecioxhora(source.getPrecio());
        cancha.setHoraApertura(source.getHoraApertura());
        cancha.setHoraCierre(source.getHoraCierre());
        cancha.setTelefono(source.getTelefono());
        return cancha;
    }
}