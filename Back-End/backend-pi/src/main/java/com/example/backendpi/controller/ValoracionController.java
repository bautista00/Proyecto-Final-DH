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

    @PostMapping("/detailcancha/{id}/valoracion")
    public ResponseEntity<ValoracionDTO> agregarValoracion(@PathVariable("id") Long id, @RequestBody ValoracionDTO valoracionDTO,@RequestParam String token) throws ResourceNotFoundException {
        valoracionDTO.setCanchaID(id);
        return ResponseEntity.ok(valoracionService.agregarValoracion(valoracionDTO,token));
    }


    @DeleteMapping("nose/{id}")
    public ResponseEntity<String> eliminarValoracion(@PathVariable Long id) throws ResourceNotFoundException{
        valoracionService.eliminarValoracion(id);
        return ResponseEntity.ok("Se elimino su valoracion con exito");
    }




}
