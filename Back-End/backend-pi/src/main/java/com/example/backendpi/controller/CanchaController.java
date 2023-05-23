package com.example.backendpi.controller;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.service.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/canchas")
public class CanchaController {
    private CanchaService canchaService;
    private PrestadorService prestadorService;


    @Autowired
    public CanchaController(CanchaService canchaService, PrestadorService prestadorService) {
        this.canchaService = canchaService;
        this.prestadorService = prestadorService;
    }

    @PostMapping
    public ResponseEntity<Cancha> agregarCancha(@RequestBody Cancha cancha){
        if(prestadorService.buscarXId(cancha.getPrestador().getId()).isPresent()){
            return ResponseEntity.ok(canchaService.guardar(cancha));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <Cancha> buscarCancha(@PathVariable Long id){
        Optional<Cancha> optionalCancha= canchaService.buscarXId(id);
        if(optionalCancha.isPresent()){
            return ResponseEntity.ok(optionalCancha.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Cancha>> buscarTodos(){
        if(canchaService.buscarTodos().size()>0) {
            return ResponseEntity.ok(canchaService.buscarTodos());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCancha(@PathVariable Long id){
        canchaService.borrarXId(id);
        return ResponseEntity.ok("Se elimino la cancha con el id: "+id);
    }

    @PutMapping
    public ResponseEntity<Cancha> actualizarCancha(@RequestBody Cancha cancha){
        return ResponseEntity.ok(canchaService.actualizar(cancha));
    }

}
