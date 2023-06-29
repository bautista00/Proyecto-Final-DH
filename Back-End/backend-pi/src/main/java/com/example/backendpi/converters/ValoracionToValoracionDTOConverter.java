package com.example.backendpi.converters;


import com.example.backendpi.domain.Valoracion;
import com.example.backendpi.dto.ValoracionDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ValoracionToValoracionDTOConverter implements Converter<Valoracion, ValoracionDTO> {


    @Override
    public ValoracionDTO convert(Valoracion source) {
        ValoracionDTO valoracionDTO = new ValoracionDTO();
        valoracionDTO.setId(source.getId());
        valoracionDTO.setDescripcion(source.getDescripcion());
        valoracionDTO.setPuntuacion(source.getPuntuacion());
        valoracionDTO.setCanchaID(source.getCancha().getId());
        valoracionDTO.setUserID(source.getUser().getId());
        valoracionDTO.setUserName(source.getUser().getName());
        valoracionDTO.setApellido(source.getUser().getApellido());
        return valoracionDTO;
    }
}
