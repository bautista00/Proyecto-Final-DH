package com.example.backendpi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaApertura;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaCierre ;

    private Double promedioPuntuacion;

    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Turno> turnoList = new ArrayList<>();

    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Criterios> criteriosList=new ArrayList<>();

    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Valoracion> valoracionList = new ArrayList<>();

    @OneToOne(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Images images ;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "servicio_cancha",
            joinColumns = { @JoinColumn(name = "cancha_id") },
            inverseJoinColumns = { @JoinColumn(name = "servicio_id") })
    private List<Servicio> servicioList = new ArrayList<>();

    private String descripcion;


}
