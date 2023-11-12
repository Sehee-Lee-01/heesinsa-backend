package com.sehee.heesinsa.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationError(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body("Double-check your input requirements.");
    }
}