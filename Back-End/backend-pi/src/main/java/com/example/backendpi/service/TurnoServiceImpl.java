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
import com.example.backendpi.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TurnoServiceImpl implements TurnoService{

    private final TurnoRepository turnoRepository;
    private final UserRepository userRepository;
    private final TurnoToTurnoDTOConverter turnoToTurnoDTOConverter;
    private final TurnoDTOToTurnoConverter turnoDTOToTurnoConverter;
    private final CanchaService canchaService;
    private final CanchaRepository canchaRepository;
    private final JwtService jwtService;

    @Override
    public Turno guardar(TurnoDTO turnoDTO) throws ResourceNotFoundException {
        if (canchaService.buscarXId(turnoDTO.getIdCancha()) != null && userRepository.findById(turnoDTO.getIdUser()).isPresent()
          && turnoRepository.findByFechaAndCancha(turnoDTO.getFecha(),canchaRepository.findByNombre(turnoDTO.getNombreCancha()))!=null){
            Turno turno = turnoDTOToTurnoConverter.convert(turnoDTO);
            turno.setUser(userRepository.findById(turnoDTO.getIdUser()).get());
            turno.setCancha(canchaRepository.findById(turnoDTO.getIdCancha()).get());
            turno.setCompletado(false);
            return (turnoRepository.save(turno));
        }else {
            throw new ResourceNotFoundException("No se pudo guardar correctamente el turno seleccionado");
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
    public TurnoDTO buscarPorCliente(String token) throws ResourceNotFoundException{
        Turno turno = turnoRepository.findByUser(userRepository.findByEmail(jwtService.extractUserName(token)));
        if(turno != null && !turno.isCompletado()){
            return turnoToTurnoDTOConverter.convert(turno);
        }else {
            throw new ResourceNotFoundException("No se encontro un turno asocioado a este cliente llamado: " + turno.getUser().getName());
        }
    }

    @Override
    public List<TurnoDTO> buscarPorCancha(Cancha cancha) throws ResourceNotFoundException{
        List<Turno> turnoList = turnoRepository.findByCancha(cancha);
        List<TurnoDTO> turnoDTOS = new ArrayList<>();
        if (turnoList.size()>0){
            for (Turno turno : turnoList) {
                turnoDTOS.add(turnoToTurnoDTOConverter.convert(turno));
            }
            return turnoDTOS;
        }
        else {
            throw new ResourceNotFoundException("No se encontro un turno asociado con la cancha seleccionada");
        }
    }







    //    @Override
//    public Optional<TurnoDTO> buscarXId(Long id) throws ResourceNotFoundException {
//        Optional<Turno> turno  = turnoRepository.findById(id);
//        if(turno.isPresent()){
//            return Optional.of(turnoToTurnoDTOConverter.convert(turno.get()));
//        }else {
//            throw new ResourceNotFoundException("No existe el turno  buscado con ese id" + id);
//        }
//
//    }

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
    public List<TurnoDTO> historialCanchaUsuario(String token) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(jwtService.extractUserName(token));
        List<Turno> turnoList = turnoRepository.findByUserWithFecha(user.getId());
        List<TurnoDTO> turnoDTOS = new ArrayList<>();
        if (turnoList.size() > 0) {
            for (Turno turno : turnoList) {
                turnoDTOS.add(turnoToTurnoDTOConverter.convert(turno));
            }
            throw new ResourceNotFoundException("La lista esta vacia");
        }
        return turnoDTOS;
    }

//    @Override
//    public TurnoDTO actualizar(TurnoDTO turnoDTO) throws ResourceNotFoundException{
//        if(userRepository.findById(turnoDTO.getIdUser()).isPresent() && canchaService.buscarXId(turnoDTO.getIdCancha()).isPresent()){
//            return turnoToTurnoDTOConverter.convert(turnoRepository.save(turnoDTOToTurnoConverter.convert(turnoDTO)));
//        }else {
//            throw new ResourceNotFoundException("No se pudo actualizar correctamente el turno");
//        }
//    }

}
