package com.example.backendpi.converters;

import com.example.backendpi.domain.Turno;
import com.example.backendpi.dto.TurnoDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TurnoToTurnoDTOConverter implements Converter<Turno, TurnoDTO> {
    @Override
    public TurnoDTO convert(Turno source) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(source.getId());
        turnoDTO.setDireccionCancha(source.getCancha().getDomicilio().getCalle());
        turnoDTO.setBarrioCancha(source.getCancha().getDomicilio().getBarrio().getNombre());
        turnoDTO.setPrecio(source.getCancha().getPrecioxhora());
        turnoDTO.setFecha(source.getFecha());
        turnoDTO.setHoras(source.getHoras());
        turnoDTO.setNombreCancha(source.getCancha().getNombre());
        turnoDTO.setNombreUser(source.getUser().getName());
        turnoDTO.setIdUser(source.getUser().getId());
        turnoDTO.setIdCancha(source.getCancha().getId());
        turnoDTO.setCompletado(source.isCompletado());
        return turnoDTO;
    }
}
