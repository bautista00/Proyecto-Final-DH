package com.example.backendpi.dto;

import com.example.backendpi.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class CanchaDTO {
        private Long id;
        private Categoria categoria;
        private Domicilio domicilio;
        private Double precio;
        private String telefono;
        private String nombre;
        private LocalTime horaApertura;
        private LocalTime horaCierre;
        private List<Criterios> criteriosList;
        private List<ImagesDTO> imagesDTOSList;
        private List<ValoracionDTO> valoracionList;
        private Double promedio;
}
