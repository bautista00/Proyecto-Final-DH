package com.example.backendpi.exceptions;

public class BearerTokenException extends RuntimeException {
    public BearerTokenException(String message) {
        super(message);
    }
}
