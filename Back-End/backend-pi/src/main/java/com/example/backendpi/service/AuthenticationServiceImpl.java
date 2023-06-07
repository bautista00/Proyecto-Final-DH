package com.example.backendpi.service;

import com.example.backendpi.dto.AuthenticationResponse;
import com.example.backendpi.dto.LoginRequest;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) throws ResourceNotFoundException {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        String jwt =jwtService
                .generateToken((UserDetails) authentication.getPrincipal());

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .rol(userRepository.findByEmail(loginRequest.getUsername()).getRole().name())
                .build();
    }

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        UserDetails userDetails = userService.createUser(signUpRequest);
        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(userDetails))
                .build();
    }
}
