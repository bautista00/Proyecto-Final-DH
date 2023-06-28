package com.example.backendpi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancha_id")
    @JsonIgnore
    private Cancha cancha;

}
