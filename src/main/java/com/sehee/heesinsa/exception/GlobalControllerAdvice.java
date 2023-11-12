package com.sehee.heesinsa.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationError(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body("Double-check your input requirements.");
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleValidationError(NoSuchElementException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}