package com.example.backendpi.service;

import com.amazonaws.services.mq.model.NotFoundException;
import com.example.backendpi.converters.TurnoDTOToTurnoConverter;
import com.example.backendpi.converters.TurnoToTurnoDTOConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.TurnoDTO;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.TurnoRepository;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class TurnoServiceImpl implements TurnoService{

    private final TurnoRepository turnoRepository;
    private final UserRepository userRepository;
    private final TurnoToTurnoDTOConverter turnoToTurnoDTOConverter;
    private final TurnoDTOToTurnoConverter turnoDTOToTurnoConverter;
    private final CanchaService canchaService;

    @Override
    public TurnoDTO guardar(TurnoDTO turnoDTO) {
        if (canchaService.buscarXId(turnoDTO.getIdCancha()).isPresent() && userRepository.findById(turnoDTO.getIdUser()).isPresent()){
            return turnoToTurnoDTOConverter.convert(turnoRepository.save(turnoDTOToTurnoConverter.convert(turnoDTO)));
        }else {
            throw new NotFoundException("F");
        }
    }

    @Override
    public Optional<TurnoDTO> buscarXId(Long id) {
        return Optional.empty();
    }

    @Override
    public void borrarXId(Long id) {
       if( turnoRepository.findById(id).isPresent()){
           turnoRepository.deleteById(id);
       }else {
           throw new NotFoundException("No se encontro rey de reyes");
       }
    }

    @Override
    public List<TurnoDTO> buscarTodos() {
        if(turnoRepository.findAll().size()>0){
            List<TurnoDTO> turnoDTOS= new ArrayList<>();
            List<Turno> turnos= turnoRepository.findAll();
            if(turnos.size()>0) {
                for (Turno turno : turnos) {
                    turnoDTOS.add(turnoToTurnoDTOConverter.convert(turno));
                }
            }
            return turnoDTOS;
        }else{
            throw new NotFoundException("No hay lista xd");
        }
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turnoDTO) {
        if(userRepository.findById(turnoDTO.getIdUser()).isPresent() && canchaService.buscarXId(turnoDTO.getIdCancha()).isPresent()){
            return turnoToTurnoDTOConverter.convert(turnoRepository.save(turnoDTOToTurnoConverter.convert(turnoDTO)));
        }else {
            throw new NotFoundException("Esto no lo arreglo yo");
        }
    }

    @Override
    public TurnoDTO buscarPorCliente(User user) {
        if(turnoRepository.findByUser(user) != null){
        return turnoRepository.findByUser(user);
        }else {
            throw new NotFoundException("Esto lo arregla otro");
        }
    }

    @Override
    public List<TurnoDTO> buscarPorCancha(Cancha cancha) {
        List<TurnoDTO> turnoDTOS = turnoRepository.findByCancha(cancha);
        if (turnoDTOS.size()>0){
            return turnoDTOS;
        }
        else {
            throw new NotFoundException("Lista 404");
        }
    }
}
