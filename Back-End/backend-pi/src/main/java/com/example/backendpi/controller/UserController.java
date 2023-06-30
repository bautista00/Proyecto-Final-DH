package com.example.backendpi.controller;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.UserDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all/getuser")
    public ResponseEntity<UserDTO> getUser (@RequestParam(value = "token") String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.getUser(token));
    }

    @DeleteMapping("/admin/deleteClient")
    public ResponseEntity<String> borrarUsuario(Long id) throws ResourceNotFoundException{
        userService.borrarCliente(id);
        return ResponseEntity.ok("Se borro el usuario con id "+ id);
    }

    @GetMapping("/admin/getallusers")
    public ResponseEntity<List<User>> listarUsuarios()throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.listarTodos());
    }


    @GetMapping("/user/listaFavs")
    public ResponseEntity<List<CanchaDTO>> listaFavs(@RequestParam(value = "token") String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.listarCanchasFav(token));
    }

    @PutMapping("/user/modificar")
    public ResponseEntity<String> modificarUser(@RequestBody UserDTO userDTO,@RequestParam(value = "token")String token) throws ResourceNotFoundException{
        userService.modificarUser(userDTO,token);
        return ResponseEntity.ok("Ya se actualizo el user");
    }




}
