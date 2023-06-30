package com.example.backendpi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


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
    @OneToOne(cascade = CascadeType.REMOVE)
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
    private List<Criterios> criteriosList = new ArrayList<>();

    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Valoracion> valoracionList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "images_id", referencedColumnName = "id")
    private Images images;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "servicio_cancha",
            joinColumns = { @JoinColumn(name = "cancha_id") },
            inverseJoinColumns = { @JoinColumn(name = "servicio_id") })
    private List<Servicio> servicioList = new ArrayList<>();

    private String descripcion;


    @ManyToMany(mappedBy = "canchas")
    private List<CanchasFavoritas> listasCanchasFavoritas = new ArrayList<>();

}
