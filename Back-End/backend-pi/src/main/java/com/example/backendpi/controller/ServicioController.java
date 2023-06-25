package com.example.backendpi.controller;



import com.example.backendpi.domain.Servicio;

import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.ServicioService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class ServicioController {


    private final ServicioService servicioService;
    @GetMapping("/findAllServicio")
    public ResponseEntity<List<Servicio>> buscarServicios()throws ResourceNotFoundException {
        return ResponseEntity.ok(servicioService.buscarTodos());
    }

    @PostMapping("/admin/addServicio")
    public ResponseEntity<Servicio> agregarServicio (@RequestParam(value = "servicio")String servicio) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Servicio servicio1 = objectMapper.readValue(servicio, Servicio.class);
        return ResponseEntity.ok(servicioService.guardarServicio(servicio1));
    }


    @DeleteMapping("/admin/deleteServicio/{id}")
    public ResponseEntity<String> borrarServicio (@PathVariable Long id) throws ResourceNotFoundException{
        servicioService.borrarXId(id);
        return ResponseEntity.ok("El servicio  con id " +id+ " se elimino correctamente");
    }

}
























