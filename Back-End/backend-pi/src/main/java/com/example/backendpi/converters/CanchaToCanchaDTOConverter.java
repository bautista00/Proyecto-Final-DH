package com.example.backendpi.converters;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.dto.CanchaDTO;
import org.springframework.core.convert.converter.Converter;


public class CanchaToCanchaDTOConverter implements Converter<Cancha, CanchaDTO> {
    @Override
    public CanchaDTO convert(Cancha source) {
        CanchaDTO  canchaDTO = new CanchaDTO();
        canchaDTO.setDeporte(source.getDeporte());
        canchaDTO.setPrecio(source.getPrecioxhora());
        canchaDTO.setDomicilio(source.getDomicilio());
        canchaDTO.setNombre(source.getNombre());
        canchaDTO.setTelefono(source.getTelefono());
        canchaDTO.setHoraApertura(source.getHoraApertura());
        canchaDTO.setHoraCierre(source.getHoraCierre());
        return canchaDTO;
    }
}
