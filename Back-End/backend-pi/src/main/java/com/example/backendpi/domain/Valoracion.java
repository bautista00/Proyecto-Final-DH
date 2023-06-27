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
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Double puntuacion;
    @ManyToOne
    @JoinColumn(name = "cancha_id",referencedColumnName = "id")
    @JsonIgnore
    private Cancha cancha;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
}
