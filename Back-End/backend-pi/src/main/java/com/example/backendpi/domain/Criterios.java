package com.example.backendpi.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criterios {
    private Long id;
    private String descripcion;
    private CriterioTitulo criterioTitulo;

}