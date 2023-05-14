package com.example.backendpi.domain;

public class Cliente extends Usuario{
    private Domicilio domicilio;
    private String nombre;
    private String dni;
    private String telefono;
    private Turno turno;

    public Cliente(String email, String password, Rol rol, Domicilio domicilio, String nombre, String dni, String telefono, Turno turno) {
        super(email, password, rol);
        this.domicilio = domicilio;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.turno = turno;
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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

}
