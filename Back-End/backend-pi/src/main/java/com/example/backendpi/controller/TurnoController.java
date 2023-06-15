package com.example.backendpi.controller;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.TurnoDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.repository.UserRepository;
import com.example.backendpi.service.CanchaService;
import com.example.backendpi.service.TurnoService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class TurnoController {

    private final TurnoService turnoService;

    private final CanchaService canchaService;

    private final UserRepository userRepository;


    @PostMapping("/user/createturno")
    public ResponseEntity<Turno> agregarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException {
        if(canchaService.buscarXId(turno.getIdCancha()) != null && userRepository.findById(turno.getIdUser()).isPresent()){
        return ResponseEntity.ok(turnoService.guardar(turno));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/all/deleteturno/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el turno con ese id" + id);
    }

    @GetMapping("/user/findxuserturnos")
    public ResponseEntity<TurnoDTO> buscarPorUser(@RequestBody User user) throws ResourceNotFoundException{
       return ResponseEntity.ok(turnoService.buscarPorCliente(user));
    }

    @GetMapping("/admin/findxcanchasturnos")
    public ResponseEntity<List<TurnoDTO>> buscarPorCanchas (@RequestBody Cancha cancha) throws ResourceNotFoundException{
        return ResponseEntity.ok(turnoService.buscarPorCancha(cancha));
    }

//    @PutMapping("/all/updateShift")
//    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
//        return ResponseEntity.ok(turnoService.actualizar(turnoDTO));
//    }

//    @GetMapping("/all/{id}")
//    public ResponseEntity<TurnoDTO> buscarTurnoXId(@PathVariable Long id) throws ResourceNotFoundException {
//      Optional<TurnoDTO> optionalTurno = turnoService.buscarXId(id);
//      if (optionalTurno.isPresent()){
//          return ResponseEntity.ok(optionalTurno.get());
//      } else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//      }
//    }

//    @GetMapping("/user/ListAll")
//    public ResponseEntity<List<TurnoDTO>> buscarTodos()throws ResourceNotFoundException{
//      List<TurnoDTO> turnoDTOS = turnoService.buscarTodos();
//      return ResponseEntity.ok(turnoDTOS);
//    }
}
