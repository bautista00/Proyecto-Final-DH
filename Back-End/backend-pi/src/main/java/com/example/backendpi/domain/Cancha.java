package com.example.backendpi.domain;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private LocalTime horaApertura;

    private LocalTime horaCierre;


    @OneToMany(mappedBy = "cancha", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Turno> turnoList = new HashSet<>();

    public Cancha(Deporte deporte, Domicilio domicilio, Double precioxhora, String telefono, Usuario usuario, LocalTime fechaApertura, LocalTime fechaCierre, Set<Turno> turnoList) {
        this.deporte = deporte;
        this.domicilio = domicilio;
        this.precioxhora = precioxhora;
        this.telefono = telefono;
        this.usuario = usuario;
        this.horaApertura = fechaApertura;
        this.horaCierre = fechaCierre;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime fechaApertura) {
        this.horaApertura = fechaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime fechaCierre) {
        this.horaCierre = fechaCierre;
    }

    public Set<Turno> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(Set<Turno> turnoList) {
        this.turnoList = turnoList;
    }
}
