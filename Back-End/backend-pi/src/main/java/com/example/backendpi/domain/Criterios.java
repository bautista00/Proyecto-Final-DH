package com.example.backendpi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criterios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private CriterioTitulo criterioTitulo;
    @ManyToOne
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    private Cancha cancha;

}
