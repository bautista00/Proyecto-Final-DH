package com.example.backendpi.domain;

import javax.persistence.Entity;

@Entity
public class Usuario {
    private Long id;
    private String nombre;
    private String DNI;
    private String telefono;
    private String email;
    private String password;
    private Turno turno;

    private Domicilio domicilio;

    //falta rol//


    public Usuario(String nombre, String DNI, String telefono, String email, String password, Turno turno, Domicilio domicilio) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.turno = turno;
        this.domicilio = domicilio;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}


