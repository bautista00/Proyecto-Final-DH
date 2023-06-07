package com.example.backendpi.service;

import com.amazonaws.services.mq.model.NotFoundException;
import com.example.backendpi.converters.TurnoDTOToTurnoConverter;
import com.example.backendpi.converters.TurnoToTurnoDTOConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.TurnoDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.CanchaRepository;
import com.example.backendpi.repository.TurnoRepository;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final CanchaRepository canchaRepository;

    @Override
    public Turno guardar(TurnoDTO turnoDTO) throws ResourceNotFoundException {
        if (canchaService.buscarXId(turnoDTO.getIdCancha()).isPresent() && userRepository.findById(turnoDTO.getIdUser()).isPresent()){
            Turno turno = turnoDTOToTurnoConverter.convert(turnoDTO);
            turno.setUser(userRepository.findById(turnoDTO.getIdUser()).get());
            turno.setCancha(canchaRepository.findById(turnoDTO.getIdCancha()).get());
            return (turnoRepository.save(turno));
        }else {
            throw new ResourceNotFoundException("No se pudo guardar correctamente el turno seleccionado");
        }
    }

    @Override
    public Optional<TurnoDTO> buscarXId(Long id) throws ResourceNotFoundException {
        Optional<Turno> turno  = turnoRepository.findById(id);
        if(turno.isPresent()){
            return Optional.of(turnoToTurnoDTOConverter.convert(turno.get()));
        }else {
            throw new ResourceNotFoundException("No existe el turno  buscado con ese id" + id);
        }

    }

    @Override
    public void borrarXId(Long id) throws ResourceNotFoundException{
       if( turnoRepository.findById(id).isPresent()){
           turnoRepository.deleteById(id);
       }else {
           throw new NotFoundException("No se pudo borrar el turno seleccionado");
       }
    }

    @Override
    public List<TurnoDTO> buscarTodos() throws ResourceNotFoundException{
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
            throw new NotFoundException("No se encontro una lista de turnos");
        }
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turnoDTO) throws ResourceNotFoundException{
        if(userRepository.findById(turnoDTO.getIdUser()).isPresent() && canchaService.buscarXId(turnoDTO.getIdCancha()).isPresent()){
            return turnoToTurnoDTOConverter.convert(turnoRepository.save(turnoDTOToTurnoConverter.convert(turnoDTO)));
        }else {
            throw new ResourceNotFoundException("No se pudo actualizar correctamente el turno");
        }
    }

    @Override
    public TurnoDTO buscarPorCliente(User user) throws ResourceNotFoundException{
        if(turnoRepository.findByUser(user) != null){
        return turnoRepository.findByUser(user);
        }else {
            throw new ResourceNotFoundException("No se encontro un turno asocioado a este cliente llamado: " + user.getName());
        }
    }

    @Override
    public List<TurnoDTO> buscarPorCancha(Cancha cancha) throws ResourceNotFoundException{
        List<TurnoDTO> turnoDTOS = turnoRepository.findByCancha(cancha);
        if (turnoDTOS.size()>0){
            return turnoDTOS;
        }
        else {
            throw new ResourceNotFoundException("No se encontro un turno asociado con la cancha seleccionada");
        }
    }
}
