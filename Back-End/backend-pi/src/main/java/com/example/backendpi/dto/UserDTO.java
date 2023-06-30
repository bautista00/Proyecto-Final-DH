package com.example.backendpi.dto;

import com.example.backendpi.domain.Domicilio;
import com.example.backendpi.domain.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String CBU;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String cuil;
    private String telefono;
    private Domicilio domicilio;
}
