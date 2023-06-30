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

import java.time.LocalDateTime;
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

//    @Override
//    public Turno guardar(TurnoDTO turnoDTO) throws ResourceNotFoundException {
//        if (canchaRepository.findById(turnoDTO.getIdUser()) != null && userRepository.findById(turnoDTO.getIdUser()).isPresent()
//          && turnoRepository.findByFechaAndCancha(turnoDTO.getFecha(),canchaRepository.findByNombre(turnoDTO.getNombreCancha()))!=null){
//            Turno turno = turnoDTOToTurnoConverter.convert(turnoDTO);
//            turno.setUser(userRepository.findById(turnoDTO.getIdUser()).get());
//            turno.setCancha(canchaRepository.findById(turnoDTO.getIdCancha()).get());
//            turno.setCompletado(false);
//            return (turnoRepository.save(turno));
//        }else {
//            throw new ResourceNotFoundException("No se pudo guardar correctamente el turno seleccionado");
//        }
//    }
@Override
public Turno guardar(TurnoDTO turnoDTO, String token) throws ResourceNotFoundException {
    String userName = jwtService.extractUserName(token);
    User user = userRepository.findByEmail(userName);
    Long canchaId = turnoDTO.getIdCancha();
    LocalDateTime fecha = turnoDTO.getFecha();
    String nombreCancha = turnoDTO.getNombreCancha();

    if (user == null) {
        throw new ResourceNotFoundException("No se encontró el usuario con el token proporcionado");
    }

    Cancha cancha = canchaRepository.findById(canchaId)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró la cancha con el ID: " + canchaId));

    // Verificar si la fecha es anterior a la fecha actual
    LocalDateTime fechaActual = LocalDateTime.now();
    if (fecha.isBefore(fechaActual)) {
        throw new IllegalArgumentException("La fecha especificada es anterior a la fecha actual");
    }

    Turno turnoExistente = turnoRepository.findByFechaAndCancha(fecha, cancha);
    if (turnoExistente != null) {
        throw new ResourceNotFoundException("Ya existe un turno para la cancha y fecha especificadas");
    }

    Turno turno = new Turno();
    turno.setFecha(fecha);
    turno.setHoras(turnoDTO.getHoras());
    turno.setUser(user);
    turno.setCancha(cancha);
    turno.setCompletado(false);

    return turnoRepository.save(turno);
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
    public List<TurnoDTO> buscarPorCliente(String token) throws ResourceNotFoundException {
        List<Turno> turnoList = turnoRepository.findByUser(userRepository.findByEmail(jwtService.extractUserName(token)));
        List<TurnoDTO> turnoDTOS = new ArrayList<>();
        if (turnoList.size() > 0) {
            for (Turno turno : turnoList) {
                if (turno != null && !turno.isCompletado()) {
                    turnoDTOS.add(turnoToTurnoDTOConverter.convert(turno));
                }
            }
            return turnoDTOS;
        } else {
            throw new ResourceNotFoundException("No se encontro un turno asocioado a este cliente llamado: ");
        }
    }

    @Override
    public List<TurnoDTO> buscarPorCancha(String nombre) throws ResourceNotFoundException{
        Cancha cancha = canchaRepository.findByNombre(nombre);
        List<Turno> turnoList = turnoRepository.findByCancha2(cancha.getId());
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
        List<Turno> turnoList = turnoRepository.findByUser(userRepository.findByEmail(jwtService.extractUserName(token)));
        List<TurnoDTO> turnoDTOS = new ArrayList<>();

        if (turnoList.size() > 0) {
            for (Turno turno : turnoList) {
                if (turno.isCompletado()) { // Agregar condición de completado
                    turnoDTOS.add(turnoToTurnoDTOConverter.convert(turno));
                }
            }
            return turnoDTOS;
        }

        throw new ResourceNotFoundException("La lista está vacía");
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
