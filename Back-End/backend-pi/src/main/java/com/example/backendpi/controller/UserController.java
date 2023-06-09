package com.example.backendpi.controller;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.ClientDTO;
import com.example.backendpi.dto.OwnerDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/api/users")
//    public PageResponseDTO<UserDTO> getUsers(@PageableDefault(size = 10,page = 0) @ParameterObject Pageable pageable) {
//        return userService.getUsers(pageable);
//    }

    @GetMapping("/admin/getusers")
    public ResponseEntity<OwnerDTO> getUser (@PathVariable String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.getOwner(token));
    }

    @GetMapping("/user/getclient")
    public ResponseEntity<ClientDTO> getClient (@PathVariable String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.getClient(token));
    }

}
