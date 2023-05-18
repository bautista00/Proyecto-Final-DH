package com.example.backendpi.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cancha {

    @Id
    private Long id;
    private Deporte deporte;
    @OneToOne
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    private Double precioxhora;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "prestador_id", referencedColumnName = "id")
    private Prestador prestador;

    private LocalTime fechaApertura;

    private LocalTime fechaCierre;

    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Turno> turnoList = new HashSet<>();

    public Cancha(Deporte deporte, Domicilio domicilio, Double precioxhora, String telefono, Prestador prestador, LocalTime fechaApertura, LocalTime fechaCierre, Set<Turno> turnoList) {
        this.deporte = deporte;
        this.domicilio = domicilio;
        this.precioxhora = precioxhora;
        this.telefono = telefono;
        this.prestador = prestador;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.turnoList = turnoList;
    }

    public Cancha() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Double getPrecioxhora() {
        return precioxhora;
    }

    public void setPrecioxhora(Double precioxhora) {
        this.precioxhora = precioxhora;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Set<Turno> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(Set<Turno> turnoList) {
        this.turnoList = turnoList;
    }
}
