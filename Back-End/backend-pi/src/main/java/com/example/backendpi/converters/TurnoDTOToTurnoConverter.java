package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.TurnoDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TurnoDTOToTurnoConverter implements Converter<TurnoDTO, Turno> {
    @Override
    public Turno convert(TurnoDTO source) {
        Turno turno = new Turno();
        User user = new User();
        Cancha cancha = new Cancha();
        turno.setHoras(source.getHoras());
        turno.setFecha(source.getFecha());
        turno.setId(source.getId());
        turno.setCompletado(source.isCompletado());
        user.setId(source.getIdUser());
        user.setName(source.getNombreUser());
        cancha.setNombre(source.getNombreCancha());
        cancha.setId(source.getIdCancha());
        turno.setUser(user);
        turno.setCancha(cancha);
        return turno;

    }
}
