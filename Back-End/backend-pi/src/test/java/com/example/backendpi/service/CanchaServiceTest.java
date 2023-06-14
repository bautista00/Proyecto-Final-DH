package com.example.backendpi.service;

import com.example.backendpi.domain.User;
import com.example.backendpi.exceptions.BadRequestException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CanchaServiceTest {

    @Test
    @Order(1)
    void guardar() throws BadRequestException {
    }

    @Test
    void buscarXId() {
    }

    @Test
    void borrarXId() {
    }

    @Test
    void buscarTodos() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void buscarXCategoria() {
    }

    @Test
    void buscarPorUser() {
    }
}