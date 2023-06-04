package com.example.backendpi.dto;

import com.example.backendpi.domain.Deporte;
import com.example.backendpi.domain.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class CanchaDTO {
        private Long id;
        private Deporte deporte;
        private Domicilio domicilio;
        private Double precio;
        private String telefono;
        private String nombre;
        private LocalTime horaApertura;
        private LocalTime horaCierre;
}
