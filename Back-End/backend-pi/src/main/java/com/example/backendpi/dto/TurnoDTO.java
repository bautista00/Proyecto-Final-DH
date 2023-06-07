package com.example.backendpi.dto;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Domicilio;
import com.example.backendpi.domain.User;
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
    private Long id;
    private LocalDateTime fecha;
    private Double precio;
    private Integer horas;
    private String nombreUser;
    private Long idUser;
    private String NombreCancha;
    private Long idCancha;
    private Domicilio domicilioCancha;
}
