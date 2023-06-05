package com.example.backendpi.service;

import com.example.backendpi.domain.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    public Servicio guardarServicio(Servicio servicio);

    public Optional<Servicio> buscarXId (Long id);

    public List<Servicio> buscarTodos();

    public void borrarXId (Long id);

    public Servicio actualizar (Servicio servicio);
}
