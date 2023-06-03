package com.example.backendpi.dto;

import com.example.backendpi.domain.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoDTO {
    private LocalDateTime fecha;
    private Double precio;
    private Integer horas;
    private String nombreUser;
    private String nombreCancha;
    private Domicilio domicilioCancha;
}
