package com.example.backendpi.service;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Deporte;
import com.example.backendpi.dto.CanchaDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CanchaService {


    public Cancha guardar(CanchaDTO canchaDTO,String email);

    public Optional<CanchaDTO> buscarXId(Long id);

    public void borrarXId(Long id);

    public List<CanchaDTO> buscarTodos();
    public CanchaDTO actualizar(CanchaDTO canchaDTO);

    public List<CanchaDTO> buscarXDeporte(Deporte deporte);

    public List<CanchaDTO> buscarPorOwner(String token);
}
