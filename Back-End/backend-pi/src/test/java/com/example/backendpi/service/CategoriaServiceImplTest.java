package com.example.backendpi.service;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.repository.CategoriaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CategoriaServiceImplTest {

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    @Order(1)
    void agregarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Natación");
        categoria.setDescripcion("Piscinas");
        categoria.setUrl("natacion.jpg");

        Categoria categoriaGuardada = categoriaService.agregarCategoria(categoria);

        assertEquals("Natación",categoriaGuardada.getNombre());
    }

    @Test
    @Order(3)
    void eliminarCategoria() throws ResourceNotFoundException {
        Optional<Categoria> categoria = Optional.ofNullable(categoriaRepository.findByNombre("Natación"));
        Long id = categoria.get().getId();
        categoriaService.eliminarCategoria(id);

        Assertions.assertThrows(ResourceNotFoundException.class,()->categoriaService.eliminarCategoria(id));
    }

    @Test
    @Order(2)
    void listarCategorias() throws ResourceNotFoundException {
        Integer listaLength = categoriaService.listarCategorias().size();

        Assertions.assertNotEquals(0,listaLength);
    }
}