package com.example.backendpi.service;

import com.amazonaws.services.backup.model.MissingParameterValueException;
import com.example.backendpi.dto.SignUpRequest;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;


    @Test
    @Order(2)
    void mustLoadUserByUsername() {
        UserDetails user = userService.loadUserByUsername("estefy@mail.com");
        assertEquals("estefy@mail.com",user.getUsername());

    }

    @Test
    @Order(1)
    @Sql(statements = {"delete from users where email = 'estefy@mail.com'"})
    void mustCreateUser() {
        SignUpRequest request = new SignUpRequest("estefy@mail.com","12345678","Estefania","Escarria",
                "1234","1234567890123456789012","3125478965",true);
        UserDetails user = userService.createUser(request);

        assertEquals("estefy@mail.com",user.getUsername());
    }

    @Test
    @Order(3)
    void mustNotCreateExistentUser(){
        SignUpRequest request = new SignUpRequest("estefy@mail.com","12345678","Estefania","Escarria",
                "1234","1234567890123456789012","3125478965",true);

        Assertions.assertThrows(EntityExistsException.class,() -> userService.createUser(request));
    }

    @Test
    @Order(4)
    void mustNotCreateUserWithNullParameters(){
        SignUpRequest request = new SignUpRequest("ana@mail.com","12345678","Ana","Perez",
                "1234",null,"3125478965",true);

        Assertions.assertThrows(MissingParameterValueException.class,() -> userService.createUser(request));
    }

    @Test
    @Order(5)
    void mustNotLoadUserByUsername() {

        Assertions.assertThrows(UsernameNotFoundException.class,() -> userService.loadUserByUsername("ana@mail.com"));

    }
}