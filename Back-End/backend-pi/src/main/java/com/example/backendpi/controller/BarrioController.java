package com.example.backendpi.controller;

import com.example.backendpi.domain.Barrio;

import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.BarrioService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barrios")
@AllArgsConstructor
public class BarrioController {

    private final BarrioService barrioService;

    @PostMapping("/god/add")
    public ResponseEntity<Barrio> agregarBarrio(@RequestBody Barrio barrio) throws EntityExistsException {
        return ResponseEntity.ok(barrioService.guardar(barrio));
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Barrio>> buscarTodos () throws ResourceNotFoundException {
        return ResponseEntity.ok(barrioService.buscarTodos());
    }

    @DeleteMapping("/god/delete/{id}")
    public ResponseEntity<String> eliminarBarrio(@PathVariable Long id)throws ResourceNotFoundException{
        barrioService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el barrio con el id: " +id);
    }

}
