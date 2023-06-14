package com.example.backendpi.service;

import com.example.backendpi.domain.*;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.CanchaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CanchaServiceImplTest {

    @Autowired
    private CanchaServiceImpl canchaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private DomicilioService domicilioService;
    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private JwtService jwtService;

    @Test
    @Order(1)
    void mustSaveField() throws Exception {

        Domicilio domicilio = new Domicilio();
        domicilioService.guardar(domicilio);

        CanchaDTO canchaDTO = new CanchaDTO();
        canchaDTO.setNombre("Las pilas");
        canchaDTO.setDomicilio(domicilio);
        canchaDTO.setPrecio(80000.0);
        canchaDTO.setTelefono("1234567891");

        User user = new User();
        user.setName("Pepe");
        user.setApellido("Perez");
        user.setTelefono("1234567890");
        user.setDomicilio(domicilio);
        user.setEmail("pepe@gmail.com");
        user.setRole(Role.ADMIN);

        String token = jwtService.generateToken(user);

        MultipartFile file = new MockMultipartFile("natacion.jpg","imagen".getBytes());

        Cancha cancha = canchaService.guardar(canchaDTO,token,file);

        assertEquals("Las pilas",cancha.getNombre());

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