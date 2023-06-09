package com.example.backendpi.converters;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.ClientDTO;
import org.springframework.core.convert.converter.Converter;

public class UserToClientDTOConverter implements Converter<User, ClientDTO> {
    @Override
    public ClientDTO convert(User source) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNombre(source.getName());
        clientDTO.setApellido(source.getApellido());
        clientDTO.setEmail(source.getEmail());
        return clientDTO;
    }
}
