package com.example.backendpi.controller;

import com.example.backendpi.domain.Prestador;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.service.CanchaService;
import com.example.backendpi.service.ClienteService;
import com.example.backendpi.service.TurnoService;
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
    private ClienteService clienteService;
    private CanchaService canchaService;

    @Autowired
    public TurnoController(TurnoService turnoService, ClienteService clienteService, CanchaService canchaService) {
        this.turnoService = turnoService;
        this.clienteService = clienteService;
        this.canchaService = canchaService;
    }

    @PostMapping
    public ResponseEntity<Turno> agregarTurno(@RequestBody Turno turno){
        if(canchaService.buscarXId(turno.getCancha().getId()).isPresent() && clienteService.buscarXId(turno.getCliente().getId()).isPresent()){
        return ResponseEntity.ok(turnoService.guardar(turno));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Turno> buscarTurno(@PathVariable Long id){
        Optional<Turno> optionalTurno= turnoService.buscarXId(id);
        if(optionalTurno.isPresent()){
            return ResponseEntity.ok(optionalTurno.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        if(turnoService.buscarTodos().size()>0) {
            return ResponseEntity.ok(turnoService.buscarTodos());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        turnoService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el turno con el id: "+id);
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }
}
