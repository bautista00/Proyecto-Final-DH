package com.example.backendpi.service;

import com.example.backendpi.domain.Turno;
import com.example.backendpi.dto.TurnoDTO;
import com.example.backendpi.repository.TurnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class TurnoServiceImpl implements TurnoService{

    private final TurnoRepository turnoRepository;

    @Override
    public Turno guardar(TurnoDTO turnoDTO) {
        return null;
    }

    @Override
    public Optional<TurnoDTO> buscarXId(Long id) {
        return Optional.empty();
    }

    @Override
    public void borrarXId(Long id) {

    }

    @Override
    public List<TurnoDTO> buscarTodos() {
        return null;
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turnoDTO) {
        return null;
    }
}
