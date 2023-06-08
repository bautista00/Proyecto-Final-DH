package com.example.backendpi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancha {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;
    @OneToOne
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    private Double precioxhora;
    private String telefono;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Turno> turnoList = new HashSet<>();
    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Criterios> criteriosSet=new HashSet<>();
    private Integer puntuacion;

    private List<String> imgList;
}
