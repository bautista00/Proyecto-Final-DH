package com.example.backendpi.service;

import com.example.backendpi.domain.Turno;
import com.example.backendpi.dto.TurnoDTO;
import com.example.backendpi.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TurnoService {


    public Turno guardar(TurnoDTO turnoDTO);

    public Optional<TurnoDTO> buscarXId(Long id);

    public void borrarXId(Long id);

    public List<TurnoDTO> buscarTodos();
    public TurnoDTO actualizar(TurnoDTO turnoDTO);
}
