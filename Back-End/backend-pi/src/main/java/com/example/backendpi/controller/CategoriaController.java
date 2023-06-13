package com.example.backendpi.controller;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.CategoriaService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/findAllCategoria/")
    public ResponseEntity<List<Categoria>> buscarCategorias()throws ResourceNotFoundException {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @PostMapping("/god/addcategoria")
    public ResponseEntity<Categoria> agregarCategoria (@RequestBody  Categoria categoria) throws EntityExistsException {
        return ResponseEntity.ok(categoriaService.agregarCategoria(categoria));
    }

    @DeleteMapping("/god/deleteCategoria/{id}")
    public ResponseEntity<String> borrarCategoria (@PathVariable Long id)throws ResourceNotFoundException{
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("La categoria  con id " +id+ " se elimino correctamente");
    }
}
