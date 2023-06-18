package com.example.backendpi.dto;

import com.example.backendpi.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;
import java.util.List;

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
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaApertura;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaCierre;
    private List<Criterios> criteriosList;
    private List<ImagesDTO> imagesDTOSList;
    private List<ValoracionDTO> valoracionList;
    private Double promedio;
    private List<Servicio> servicioList;
    private String descripcion;
}

