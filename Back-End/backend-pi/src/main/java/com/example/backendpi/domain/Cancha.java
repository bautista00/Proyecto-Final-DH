package com.example.backendpi.domain;

import javax.persistence.Entity;

@Entity
public class Cancha {
    private Long id;
    private Deporte deporte;
    private Domicilio domicilio;
    private Double precioxhora;
    private Prestador prestador;

    public Cancha(Deporte deporte, Domicilio domicilio, Double precioxhora, Prestador prestador) {
        this.deporte = deporte;
        this.domicilio = domicilio;
        this.precioxhora = precioxhora;
        this.prestador = prestador;
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
}
