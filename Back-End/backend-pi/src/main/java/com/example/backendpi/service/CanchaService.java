package com.example.backendpi.service;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface CanchaService {


    public Cancha guardar(CanchaDTO canchaDTO, String email);

    public Optional<CanchaDTO> buscarXId(Long id) throws ResourceNotFoundException;

    public void borrarXId(Long id)throws ResourceNotFoundException;

    public List<CanchaDTO> buscarTodos();
    public CanchaDTO actualizar(CanchaDTO canchaDTO)throws ResourceNotFoundException;

    public List<CanchaDTO> buscarXCategoria(Categoria categoria)throws ResourceNotFoundException;

    public List<CanchaDTO> buscarPorUser(String token)throws ResourceNotFoundException;
}
