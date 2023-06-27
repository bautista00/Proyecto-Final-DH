package com.example.backendpi.service;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CategoriaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoriaService {

    public Categoria agregarCategoria(Categoria categoria, List<MultipartFile> files) throws  Exception;
    public void eliminarCategoria(Long id) throws ResourceNotFoundException;
    public List<CategoriaDTO> listarCategorias() throws ResourceNotFoundException;

}
