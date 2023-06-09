package com.example.backendpi.dto;

import java.util.List;

public class UserPageDTO extends PageResponseDTO<ClientDTO> {
    public UserPageDTO(List<ClientDTO> content) {
        super(content);
    }
}
