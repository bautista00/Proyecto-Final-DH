package com.example.backendpi.service;

import com.example.backendpi.domain.Domicilio;

import java.util.List;
import java.util.Optional;

public interface DomicilioService {

    public Domicilio guardar(Domicilio domicilio);

    public List<Domicilio> buscarTodos();

    public Domicilio actualizar(Domicilio domicilio);

    public Optional<Domicilio> buscarXId(Long id);

    public void borrarXId(Long id);
}
