package com.example.backendpi.service;

import com.example.backendpi.domain.Servicio;
import com.example.backendpi.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    public Servicio guardarServicio(Servicio servicio) throws ResourceNotFoundException;

    public Optional<Servicio> buscarXId (Long id) throws ResourceNotFoundException;

    public List<Servicio> buscarTodos() throws ResourceNotFoundException;

    public void borrarXId (Long id)throws ResourceNotFoundException;

    public Servicio actualizar (Servicio servicio);
}
