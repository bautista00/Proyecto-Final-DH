package com.example.backendpi.service;


import com.example.backendpi.domain.Barrio;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;

import java.util.List;


public interface BarrioService {

    public Barrio guardar(Barrio barrio) throws EntityExistsException;

    public List<Barrio> buscarTodos()throws ResourceNotFoundException;

    public void borrarXId(Long id)throws ResourceNotFoundException;

}
