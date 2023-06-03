package com.example.backendpi.service;

import com.example.backendpi.dto.AuthenticationResponse;
import com.example.backendpi.dto.LoginRequest;
import com.example.backendpi.dto.SignUpRequest;

public interface AuthenticationService {
    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse signUp(SignUpRequest signUpRequest);

}
