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

    @PostMapping("/user/agregarValoracion")
    public ResponseEntity<ValoracionDTO> agregarValoracion(@RequestParam(value = "canchaId") Long canchaId, @RequestBody ValoracionDTO valoracionDTO,@RequestParam(value = "token") String token) throws ResourceNotFoundException {
        return ResponseEntity.ok(valoracionService.agregarValoracion(valoracionDTO,token,canchaId));
    }


    @DeleteMapping("nose/{id}")
    public ResponseEntity<String> eliminarValoracion(@PathVariable Long id) throws ResourceNotFoundException{
        valoracionService.eliminarValoracion(id);
        return ResponseEntity.ok("Se elimino su valoracion con exito");
    }




}
