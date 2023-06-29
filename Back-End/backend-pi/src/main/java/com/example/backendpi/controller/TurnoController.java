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
    public ResponseEntity<Turno> agregarTurno(@RequestBody TurnoDTO turno,@RequestParam(value = "token")String token) throws ResourceNotFoundException {
        if(canchaService.buscarXId(turno.getIdCancha()) != null){
        return ResponseEntity.ok(turnoService.guardar(turno,token));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/all/deleteturno/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el turno con ese id" + id);
    }

    @GetMapping("/user/findxuserturnos")
    public ResponseEntity<List<TurnoDTO>> buscarPorUser(@RequestParam(value = "token") String token) throws ResourceNotFoundException{
       return ResponseEntity.ok(turnoService.buscarPorCliente(token));
    }

    @GetMapping("/owner/findxcanchasturnos")
    public ResponseEntity<List<TurnoDTO>> buscarPorCanchas (@RequestParam(value = "nombre") String nombre) throws ResourceNotFoundException{
        return ResponseEntity.ok(turnoService.buscarPorCancha(nombre));
    }

    @GetMapping("/user/historialCanchas")
    public ResponseEntity<List<TurnoDTO>> historialDeCanchas(@RequestParam (value = "token") String token)throws ResourceNotFoundException{
        return ResponseEntity.ok(turnoService.historialCanchaUsuario(token));
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
