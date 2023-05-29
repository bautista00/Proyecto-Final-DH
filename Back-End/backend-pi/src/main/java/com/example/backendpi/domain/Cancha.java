package com.example.backendpi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancha {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Deporte deporte;
    @OneToOne
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    private Double precioxhora;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private LocalTime horaApertura;

    private LocalTime horaCierre;


    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Turno> turnoList = new HashSet<>();

}
