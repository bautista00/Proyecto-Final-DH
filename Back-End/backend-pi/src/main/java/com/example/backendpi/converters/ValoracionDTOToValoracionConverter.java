package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.User;
import com.example.backendpi.domain.Valoracion;
import com.example.backendpi.dto.ValoracionDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ValoracionDTOToValoracionConverter implements Converter<ValoracionDTO, Valoracion> {
    @Override
    public Valoracion convert(ValoracionDTO source) {
        Valoracion valoracion = new Valoracion();
        Cancha cancha = new Cancha();
        User user = new User();
        valoracion.setId(source.getId());
        valoracion.setDescripcion(source.getDescripcion());
        valoracion.setPuntuacion(source.getPuntuacion());
        cancha.setId(source.getCanchaID());
        user.setName(source.getUserName());
        user.setId(source.getUserID());
        user.setApellido(source.getApellido());
        valoracion.setUser(user);
        valoracion.setCancha(cancha);
        return valoracion;
    }
}
