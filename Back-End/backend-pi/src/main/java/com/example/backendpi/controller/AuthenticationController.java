package com.example.backendpi.controller;

import com.example.backendpi.dto.AuthenticationResponse;
import com.example.backendpi.dto.LoginRequest;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid @NonNull LoginRequest loginRequest) throws ResourceNotFoundException {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@RequestBody @Valid @NonNull SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token) {
        boolean verified = authenticationService.verifyUser(token);
        if (verified) {
            return ResponseEntity.ok("Email verification successful.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification token.");
        }
    }

}