package com.example.backendpi.domain;

public class Servicios {

    private Long id;
    private String nombre;
    private String url;


    public Servicios(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    public Servicios() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
