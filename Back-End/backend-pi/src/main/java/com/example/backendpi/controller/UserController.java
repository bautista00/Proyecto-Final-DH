package com.example.backendpi.controller;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.ClientDTO;
import com.example.backendpi.dto.OwnerDTO;
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

//    @GetMapping("/api/users")
//    public PageResponseDTO<UserDTO> getUsers(@PageableDefault(size = 10,page = 0) @ParameterObject Pageable pageable) {
//        return userService.getUsers(pageable);
//    }

    @GetMapping("/admin/getowner")
    public ResponseEntity<OwnerDTO> getUser (@RequestParam(value = "token") String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.getOwner(token));
    }

    @GetMapping("/user/getclient{token}")
    public ResponseEntity<ClientDTO> getClient (@RequestParam(value = "token") String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.getClient(token));
    }

    @DeleteMapping("/god/deleteClient")
    public ResponseEntity<String> borrarUsuario(Long id) throws ResourceNotFoundException{
        userService.borrarCliente(id);
        return ResponseEntity.ok("Se borro el usuario con id "+ id);
    }

    @GetMapping("/god/getusers")
    public ResponseEntity<List<User>> listarUsuarios()throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.listarTodos());
    }

}
