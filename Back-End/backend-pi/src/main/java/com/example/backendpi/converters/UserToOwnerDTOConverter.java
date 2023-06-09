package com.example.backendpi.converters;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.OwnerDTO;
import org.springframework.core.convert.converter.Converter;

public class UserToOwnerDTOConverter implements Converter<User, OwnerDTO> {
    @Override
    public OwnerDTO convert(User source) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setApellido(source.getApellido());
        ownerDTO.setCBU(source.getCbu());
        ownerDTO.setCuil(source.getCuil());
        ownerDTO.setEmail(source.getEmail());
        ownerDTO.setDomicilio(source.getDomicilio());
        ownerDTO.setNombre(source.getName());
        ownerDTO.setTelefono(source.getTelefono());

        return ownerDTO;
    }
}
