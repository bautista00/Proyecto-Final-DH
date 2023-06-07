package com.example.backendpi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> procesoErrorRNF(ResourceNotFoundException rnf){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnf.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> procesoErrorBR(BadRequestException br){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(br.getMessage());
    }
}
