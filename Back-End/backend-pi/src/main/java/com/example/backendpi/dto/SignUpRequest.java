package com.example.backendpi.dto;

import com.example.backendpi.domain.Domicilio;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    @NotEmpty
    @Length(min = 4,max = 50)
    private String username;

    @NotEmpty
    @Length(min = 8,max = 50)
    private String password;
    @NotEmpty
    @Length(min = 1,max = 50)
    private String nombre;
    @NotEmpty
    @Length(min = 1,max = 50)
    private String apellido;


    private String cbu;


    private String cuil;

//    private Domicilio domicilio;


    private String telefono;
    private boolean verified;
}
