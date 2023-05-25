package com.example.backendpi.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Rol> setUserRol = new HashSet<>();
    private String nombre;
    private String apellido;
    private String telefono;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Turno> turnoList = new HashSet<>();
    private String cuil;
    private Integer CBU;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cancha> canchaList = new HashSet<>();


    public Usuario(String email, String password, String nombre,String apellido ,Rol... userRols) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido=apellido;
        if (setUserRol!=null){
            setUserRol.addAll(Arrays.asList(userRols));
        }
    }

    public Usuario(String email, String password, String nombre, String telefono, String cuil, Integer CBU,Rol... userRols) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cuil = cuil;
        this.CBU = CBU;
        if (setUserRol!=null){
            setUserRol.addAll(Arrays.asList(userRols));
        }
    }

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthority = new HashSet<>();
        for (Rol rol : setUserRol) {
            grantedAuthority.add(new SimpleGrantedAuthority(rol.name()));
        }
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getSetUserRol() {
        return setUserRol;
    }

    public void setSetUserRol(Set<Rol> setUserRol) {
        this.setUserRol = setUserRol;
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

    public Set<Turno> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(Set<Turno> turnoList) {
        this.turnoList = turnoList;
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

    public Set<Cancha> getCanchaList() {
        return canchaList;
    }

    public void setCanchaList(Set<Cancha> canchaList) {
        this.canchaList = canchaList;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
