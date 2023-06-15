package com.example.backendpi.converters;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setApellido(source.getApellido());
        userDTO.setCBU(source.getCbu());
        userDTO.setCuil(source.getCuil());
        userDTO.setEmail(source.getEmail());
        userDTO.setDomicilio(source.getDomicilio());
        userDTO.setNombre(source.getName());
        userDTO.setTelefono(source.getTelefono());

        return userDTO;
    }
}
