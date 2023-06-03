package com.example.backendpi.jwt;

import jakarta.servlet.http.HttpServletRequest;

public interface BearerTokenResolver {
    String resolve(HttpServletRequest request);
}
