package com.example.backendpi.service;

import com.example.backendpi.dto.AuthenticationResponse;
import com.example.backendpi.dto.LoginRequest;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.ResourceNotFoundException;

public interface AuthenticationService {
    AuthenticationResponse login(LoginRequest loginRequest) throws ResourceNotFoundException;
    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    public boolean verifyUser(String token);

}
