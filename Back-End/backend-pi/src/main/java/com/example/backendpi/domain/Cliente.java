package com.example.backendpi.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cliente extends Usuario{

    private String nombre;

    private String dni;

    private String telefono;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Turno> turnoList = new HashSet<>();

    public Cliente(String email, String password, Rol rol, Domicilio domicilio, String nombre, String dni, String telefono) {
        super(email, password, rol, domicilio);
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;

    }

    public Cliente() {
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


}
