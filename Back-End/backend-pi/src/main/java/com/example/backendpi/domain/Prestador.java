package com.example.backendpi.domain;

import java.util.ArrayList;
import java.util.List;

public class Prestador extends Usuario{
    private String nombre;
    private Domicilio domicilio;
    private String cuil;
    private String telefono;
    private Integer CBU;
    private List<Cancha> canchaList;


    public Prestador(String email, String password, Rol rol, String nombre, Domicilio domicilio, String cuil, String telefono, Integer CBU) {
        super(email, password, rol);
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.cuil = cuil;
        this.telefono = telefono;
        this.CBU = CBU;
        this.canchaList = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
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

    public List<Cancha> getCanchaList() {
        return canchaList;
    }

    public void setCanchaList(List<Cancha> canchaList) {
        this.canchaList = canchaList;
    }
}
