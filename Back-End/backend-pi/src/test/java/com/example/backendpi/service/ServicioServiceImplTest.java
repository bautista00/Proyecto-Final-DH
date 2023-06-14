package com.example.backendpi.service;

import com.example.backendpi.domain.Servicio;
import com.example.backendpi.repository.ServicioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ServicioServiceImplTest {

    @Autowired
    private ServicioServiceImpl servicioService;

    @Autowired
    private ServicioRepository servicioRepository;

    @Test
    @Order(1)
    void guardarServicio() {
        Servicio servicio = new Servicio();
        servicio.setNombre("Piscina");
        servicio.setDescripcion("Piscina");
        servicio.setUrl("piscina.jpg");

        Servicio servicioGuardado = servicioService.guardarServicio(servicio);

        assertEquals("Piscina",servicioGuardado.getNombre());
    }

    @Test
    @Order(2)
    void buscarXId() {
        Servicio servicio = servicioRepository.findByNombre("Piscina");
        Long id = servicio.getId();

        Optional<Servicio> servicioBuscado = servicioService.buscarXId(id);
        assertTrue(servicioBuscado.isPresent());
    }

    @Test
    @Order(3)
    void buscarTodos() {
        Integer length = servicioService.buscarTodos().size();

        assertNotEquals(0,length);
    }

    @Test
    @Order(4)
    void borrarXId() {
        Servicio servicio = servicioRepository.findByNombre("Piscina");
        Long id = servicio.getId();

        servicioService.borrarXId(id);

        Assertions.assertThrows(EntityNotFoundException.class,()->servicioService.borrarXId(id));
    }


}
