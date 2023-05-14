package com.example.backendpi.controller;

import com.example.backendpi.domain.Cliente;
import com.example.backendpi.domain.Prestador;
import com.example.backendpi.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {
    private PrestadorService prestadorService;
    @Autowired
    public PrestadorController(PrestadorService prestadorService) {
        this.prestadorService = prestadorService;
    }

    @PostMapping
    public ResponseEntity<Prestador> agregarPrestador(@RequestBody Prestador prestador){
        return ResponseEntity.ok(prestadorService.guardar(prestador));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Prestador> buscarPrestador(@PathVariable Long id){
        Optional<Prestador> optionalPrestador= prestadorService.buscarXId(id);
        if(optionalPrestador.isPresent()){
            return ResponseEntity.ok(optionalPrestador.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Prestador>> buscarTodos(){
        if(prestadorService.buscarTodos().size()>0) {
            return ResponseEntity.ok(prestadorService.buscarTodos());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPrestador(@PathVariable Long id){
        prestadorService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el prestador con el id: "+id);
    }

    @PutMapping
    public ResponseEntity<Prestador> actualizarPrestador(@RequestBody Prestador prestador){
        return ResponseEntity.ok(prestadorService.actualizar(prestador));
    }
}
