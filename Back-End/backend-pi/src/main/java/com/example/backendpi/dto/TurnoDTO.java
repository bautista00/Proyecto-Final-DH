package com.example.backendpi.dto;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Domicilio;
import com.example.backendpi.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoDTO {
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private DateTime fecha;
    private Double precio;
    private Integer horas;
    private String nombreUser;
    private Long idUser;
    private String nombreCancha;
    private Long idCancha;
    private String direccionCancha;
    private String barrioCancha;
}
