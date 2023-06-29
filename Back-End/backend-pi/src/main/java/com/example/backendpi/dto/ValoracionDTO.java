package com.example.backendpi.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValoracionDTO {
    private Long id;
    private Double puntuacion;
    private String descripcion;
    private Long canchaID;
    private Long userID;
    private String apellido;
    private String userName;
}
