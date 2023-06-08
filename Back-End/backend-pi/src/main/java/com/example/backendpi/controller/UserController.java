package com.example.backendpi.controller;

import com.example.backendpi.domain.User;
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

    @GetMapping("/all/getusers")
    public ResponseEntity<User> getUser (@PathVariable String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.getUser(token));
    }
}
