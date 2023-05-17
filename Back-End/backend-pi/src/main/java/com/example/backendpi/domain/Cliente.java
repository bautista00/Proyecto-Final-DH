package com.example.backendpi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Cliente extends Usuario{

    private Domicilio domicilio;

    private String nombre;

    private String dni;

    private String telefono;

    private List<Turno> turnoList;

    public Cliente(String email, String password, Rol rol, Domicilio domicilio, String nombre, String dni, String telefono) {
        super(email, password, rol);
        this.domicilio = domicilio;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        turnoList = new ArrayList<>();
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Turno> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(List<Turno> turnoList) {
        this.turnoList = turnoList;
    }
}
