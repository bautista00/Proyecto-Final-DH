package com.example.backendpi.dto;

public class PrestadorGuardar {

    private Long id;
    private String email;
    private String password;
    private String nombre;
    private String telefono;
    private String cuil;
    private Integer CBU;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Integer getCBU() {
        return CBU;
    }

    public void setCBU(Integer CBU) {
        this.CBU = CBU;
    }
}
