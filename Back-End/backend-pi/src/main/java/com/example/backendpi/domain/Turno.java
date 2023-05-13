package com.example.backendpi.domain;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Turno {
    private Long id;
    private LocalDateTime fecha;
    private Double precio;
    private Integer horas;
    private Usuario usuario;
    private Cancha cancha;


    public Turno(LocalDateTime fecha, Double precio, Integer horas, Usuario usuario, Cancha cancha) {
        this.fecha = fecha;
        this.precio = precio;
        this.horas = horas;
        this.usuario = usuario;
        this.cancha = cancha;
    }

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }
}
