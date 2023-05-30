package com.example.backendpi.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;

    @Override
    public String extractUserName(String token) {
        return jwtUtil.extractUserName(token, jwtConfig.secretKey());
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return jwtUtil.generateToken(userDetails,
                System.currentTimeMillis(),
                jwtConfig.expiration(),
                jwtConfig.secretKey());
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, String username) {
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return jwtUtil.extractExpiration(token, jwtConfig.secretKey()).before(new Date(System.currentTimeMillis()));
    }

}