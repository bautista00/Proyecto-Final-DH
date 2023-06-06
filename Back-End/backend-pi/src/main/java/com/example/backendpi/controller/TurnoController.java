package com.example.backendpi.controller;

import com.example.backendpi.domain.Turno;
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
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    private final CanchaService canchaService;

    private final UserRepository userRepository;


    @PostMapping
    public ResponseEntity<Turno> agregarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException {
        if(canchaService.buscarXId(turno.getIdCancha()).isPresent() && userRepository.findById(turno.getIdUser()).isPresent()){
        return ResponseEntity.ok(turnoService.guardar(turno));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoXId(@PathVariable Long id) throws ResourceNotFoundException {
      Optional<TurnoDTO> optionalTurno = turnoService.buscarXId(id);
      if (optionalTurno.isPresent()){
          return ResponseEntity.ok(optionalTurno.get());
      } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDTO>> buscarTodos()throws ResourceNotFoundException{
      List<TurnoDTO> turnoDTOS = turnoService.buscarTodos();
      return ResponseEntity.ok(turnoDTOS);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el turno con ese id" + id);
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.actualizar(turnoDTO));
    }
}
