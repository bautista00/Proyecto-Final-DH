package com.example.backendpi.domain;

import com.example.backendpi.service.ClienteService;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Turno {
    private Long id;
    private LocalDateTime fecha;
    private Double precio;
    private Integer horas;
    private Cliente cliente;
    private Cancha cancha;


    public Turno(LocalDateTime fecha, Double precio, Integer horas, Cliente cliente, Cancha cancha) {
        this.fecha = fecha;
        this.precio = precio;
        this.horas = horas;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }
}
