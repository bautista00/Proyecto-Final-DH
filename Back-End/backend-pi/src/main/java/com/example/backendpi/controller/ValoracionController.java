package com.example.backendpi.controller;

import com.example.backendpi.dto.ValoracionDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.ValoracionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class ValoracionController {

    private final ValoracionService valoracionService;

    @PostMapping("nose")
    public ResponseEntity<ValoracionDTO> agregarValoracion(@RequestParam(value = "valoracion") ValoracionDTO valoracionDTO)throws ResourceNotFoundException {
        return ResponseEntity.ok(valoracionService.agregarValoracion(valoracionDTO));
    }

    @DeleteMapping("nose/{id}")
    public ResponseEntity<String> eliminarValoracion(@PathVariable Long id) throws ResourceNotFoundException{
        valoracionService.eliminarValoracion(id);
        return ResponseEntity.ok("Se elimino su valoracion con exito");
    }




}
