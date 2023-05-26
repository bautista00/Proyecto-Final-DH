package com.example.backendpi.controller;

import com.example.backendpi.domain.Domicilio;
import com.example.backendpi.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    private DomicilioService domicilioService;
    @Autowired
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @PostMapping
    public ResponseEntity<Domicilio> agregarDomicilio(@RequestBody Domicilio domicilio){
        return ResponseEntity.ok(domicilioService.guardar(domicilio));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Domicilio> buscarDomicilio(@PathVariable Long id){
        Optional<Domicilio> optionalDomicilio= domicilioService.buscarXId(id);
        if(optionalDomicilio.isPresent()){
            return ResponseEntity.ok(optionalDomicilio.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Domicilio>> buscarTodos(){
        if(domicilioService.buscarTodos().size()>0) {
            return ResponseEntity.ok(domicilioService.buscarTodos());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long id){
        domicilioService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el domicilio con el id: "+id);
    }

    @PutMapping
    public ResponseEntity<Domicilio> actualizarDomicilio(@RequestBody Domicilio domicilio){
        return ResponseEntity.ok(domicilioService.actualizar(domicilio));
    }
}