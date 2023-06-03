package com.example.backendpi.converters;

import com.example.backendpi.domain.Turno;
import com.example.backendpi.dto.TurnoDTO;
import org.springframework.core.convert.converter.Converter;

public class TurnoToTurnoDTOConverter implements Converter<Turno, TurnoDTO> {
    @Override
    public TurnoDTO convert(Turno source) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setDomicilioCancha(source.getCancha().getDomicilio());
        turnoDTO.setPrecio(source.getPrecio());
        turnoDTO.setFecha(source.getFecha());
        turnoDTO.setHoras(source.getHoras());
        turnoDTO.setNombreUser(source.getUser().getName());
        turnoDTO.setNombreCancha(source.getCancha().getNombre());
        return turnoDTO;
    }
}
