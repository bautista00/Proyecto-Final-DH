package com.example.backendpi.dto;

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
public class LoginRequest {

    @NotEmpty
    @Length(min = 4,max = 50)
    private String username;

    @NotEmpty(message = "Invalid Name: Empty name")
    @Length(min = 4,max = 50)
    private String password;

}
