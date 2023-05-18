package com.example.backendpi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Prestador extends Usuario{
    private String nombre;

    private String cuil;
    private String telefono;
    private Integer CBU;
    @OneToMany(mappedBy = "prestador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cancha> canchaList = new HashSet<>();


    public Prestador(String email, String password, Rol rol, Domicilio domicilio, String nombre, String cuil, String telefono, Integer CBU, Set<Cancha> canchaList) {
        super(email, password, rol, domicilio);
        this.nombre = nombre;
        this.cuil = cuil;
        this.telefono = telefono;
        this.CBU = CBU;
        this.canchaList = canchaList;
    }

    public Prestador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getCBU() {
        return CBU;
    }

    public void setCBU(Integer CBU) {
        this.CBU = CBU;
    }

    public Set<Cancha> getCanchaList() {
        return canchaList;
    }

    public void setCanchaList(Set<Cancha> canchaList) {
        this.canchaList = canchaList;
    }
}
