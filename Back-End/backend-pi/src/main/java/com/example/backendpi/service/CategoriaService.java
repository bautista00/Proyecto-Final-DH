package com.example.backendpi.service;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;

import java.util.List;

public interface CategoriaService {

    public Categoria agregarCategoria(Categoria categoria) throws EntityExistsException;
    public void eliminarCategoria(Long id) throws ResourceNotFoundException;
    public List<Categoria> listarCategorias() throws ResourceNotFoundException;

}
