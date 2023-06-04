package com.example.backendpi.controller;

import com.example.backendpi.domain.Turno;
import com.example.backendpi.service.CanchaService;
import com.example.backendpi.service.TurnoService;
import com.example.backendpi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private UserServiceImpl usuarioServiceImpl;
    private CanchaService canchaService;

    @Autowired
    public TurnoController(TurnoService turnoService, UserServiceImpl usuarioServiceImpl, CanchaService canchaService) {
        this.turnoService = turnoService;
        this.usuarioServiceImpl = usuarioServiceImpl;
        this.canchaService = canchaService;
    }

//    @PostMapping
//    public ResponseEntity<Turno> agregarTurno(@RequestBody Turno turno){
//        if(canchaService.buscarXId(turno.getCancha().getId()).isPresent() && usuarioServiceImpl.buscarUsuario(turno.getUser().getId()).isPresent()){
//        return ResponseEntity.ok(turnoService.guardar(turno));
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

    @GetMapping("/{id}")
    public ResponseEntity <Turno> buscarTurno(@PathVariable Long id){
      return null;

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Turno>> buscarTodos(){
      return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
       return null;
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        return null;
    }
}
