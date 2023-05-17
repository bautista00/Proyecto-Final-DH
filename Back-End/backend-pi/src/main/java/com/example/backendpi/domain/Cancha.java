package com.example.backendpi.domain;

import javax.persistence.Entity;
import java.time.LocalTime;

@Entity
public class Cancha {
    private Long id;
    private Deporte deporte;
    private Domicilio domicilio;
    private Double precioxhora;
    private String telefono;
    private Prestador prestador;

    private LocalTime fechaApertura;

    private LocalTime fechaCierre;

    public Cancha(Deporte deporte, Domicilio domicilio, Double precioxhora, String telefono, Prestador prestador, LocalTime fechaApertura, LocalTime fechaCierre) {
        this.deporte = deporte;
        this.domicilio = domicilio;
        this.precioxhora = precioxhora;
        this.telefono = telefono;
        this.prestador = prestador;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
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
}
