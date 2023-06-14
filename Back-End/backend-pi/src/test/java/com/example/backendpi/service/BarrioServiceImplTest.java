package com.example.backendpi.service;

import com.example.backendpi.domain.Barrio;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.repository.BarrioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class BarrioServiceImplTest {

    @Autowired
    private BarrioServiceImpl barrioService;

    @Autowired
    private BarrioRepository barrioRepository;

    @Test
    @Order(1)
    void guardar() {
        Barrio barrio = new Barrio();
        barrio.setNombre("Las flores");

        Barrio barrioGuardado = barrioService.guardar(barrio);

        assertEquals("Las flores",barrioGuardado.getNombre());
    }

    @Test
    @Order(2)
    void buscarTodos() throws ResourceNotFoundException {
        Integer length = barrioService.buscarTodos().size();

        assertNotEquals(0,length);
    }

    @Test
    @Order(3)
    void borrarXId() throws ResourceNotFoundException {
        Barrio barrioAEliminar = barrioRepository.findByNombre("Las flores");
        Long id = barrioAEliminar.getId();

        barrioService.borrarXId(id);

        Assertions.assertThrows(ResourceNotFoundException.class,()->barrioService.borrarXId(id));
    }
}