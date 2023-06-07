package com.example.backendpi.controller;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.PageResponseDTO;
import com.example.backendpi.dto.UserDTO;
import com.example.backendpi.dto.UserPageDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/Account")
    public ResponseEntity<User> getUser (@PathVariable String token) throws ResourceNotFoundException{
        return ResponseEntity.ok(userService.getUser(token));
    }
}
