package com.example.backendpi.controller;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.CategoriaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/findAllCategoria/")
    public ResponseEntity<List<CategoriaDTO>> buscarCategorias()throws ResourceNotFoundException {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @PostMapping("/admin/addcategoria")
    public ResponseEntity<Categoria> agregarCategoria (@RequestParam(value = "categoria")String categoria, @RequestParam(value = "file") List<MultipartFile> files) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Categoria categoria1 = objectMapper.readValue(categoria, Categoria.class);
        return ResponseEntity.ok(categoriaService.agregarCategoria(categoria1,files));
    }


    @DeleteMapping("/admin/deleteCategoria/{id}")
    public ResponseEntity<String> borrarCategoria (@PathVariable Long id)throws ResourceNotFoundException{
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("La categoria  con id " +id+ " se elimino correctamente");
    }
}
