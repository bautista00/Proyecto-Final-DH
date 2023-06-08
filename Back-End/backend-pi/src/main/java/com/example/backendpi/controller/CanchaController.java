package com.example.backendpi.controller;


import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.CanchaService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping
@AllArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;
    private final UploadFileController uploadFileController;


    @PostMapping("/admin/addcancha/{token}")
    public ResponseEntity<Cancha> agregarCancha(@RequestBody CanchaDTO canchaDTO, @PathVariable String token){
        return ResponseEntity.ok(canchaService.guardar(canchaDTO,token));
    }

    @GetMapping("/detailcancha/{id}")
    public ResponseEntity<Optional<CanchaDTO>> buscarCancha(@PathVariable Long id) throws ResourceNotFoundException {
       return ResponseEntity.ok(canchaService.buscarXId(id));
    }

    @GetMapping("/listallcanchas")
    public ResponseEntity<List<CanchaDTO>> buscarTodos (){
        return ResponseEntity.ok(canchaService.buscarTodos());
    }

    @GetMapping("/listxsportcanchas")
    public ResponseEntity<List<CanchaDTO>> buscarXDeporte (@RequestBody Categoria categoria)throws ResourceNotFoundException{
        return ResponseEntity.ok(canchaService.buscarXCategoria(categoria));
    }


    @GetMapping("/admin/listxownercanchas")
    public ResponseEntity<List<CanchaDTO>> buscarXOwner (@PathVariable String token) throws ResourceNotFoundException {
        return ResponseEntity.ok(canchaService.buscarPorUser(token));
    }


    @DeleteMapping("/admin/deletecancha/{id}")
    public ResponseEntity<String> borrarCancha (@PathVariable Long id) throws ResourceNotFoundException {
        canchaService.borrarXId(id);
       return ResponseEntity.ok("La cancha con id " +id+ " se ha eliminado correctamente");
    }


    @PutMapping("/admin/updatecancha")
    public ResponseEntity<CanchaDTO> actualizarCancha(@RequestBody CanchaDTO canchaDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(canchaService.actualizar(canchaDTO));
     }


}

