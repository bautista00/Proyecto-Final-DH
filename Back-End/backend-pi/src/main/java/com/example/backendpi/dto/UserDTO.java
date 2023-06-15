package com.example.backendpi.dto;

import com.example.backendpi.domain.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String CBU;
    private String cuil;
    private String telefono;
    private Domicilio domicilio;
}
