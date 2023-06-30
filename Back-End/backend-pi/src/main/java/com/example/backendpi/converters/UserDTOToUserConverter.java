package com.example.backendpi.converters;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO source) {
        User user = new User();
        user.setRole(source.getRole());
        user.setId(source.getId());
        user.setName(source.getNombre());
        user.setApellido(source.getApellido());
        user.setCuil(source.getCuil());
        user.setCbu(source.getCBU());
        user.setTelefono(source.getTelefono());
        user.setEmail(source.getEmail());
        return user;
    }
}
