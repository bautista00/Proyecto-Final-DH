package com.example.backendpi.service;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.TurnoDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TurnoService {


    public Turno guardar(TurnoDTO turnoDTO) throws ResourceNotFoundException;

    public Optional<TurnoDTO> buscarXId(Long id)throws ResourceNotFoundException;

    public void borrarXId(Long id)throws ResourceNotFoundException;

    public List<TurnoDTO> buscarTodos()throws ResourceNotFoundException;

    public TurnoDTO actualizar(TurnoDTO turnoDTO)throws ResourceNotFoundException;

    public TurnoDTO buscarPorCliente(User user)throws ResourceNotFoundException;

    public List<TurnoDTO> buscarPorCancha(Cancha cancha)throws ResourceNotFoundException;
}
