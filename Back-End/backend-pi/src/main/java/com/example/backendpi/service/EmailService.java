package com.example.backendpi.service;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.SignUpRequest;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    void sendMail(String to, String subject, String body);
    void sendVerificationEmail(SignUpRequest signUpRequest);
    public void sendCongratsEmail(User user);
}
