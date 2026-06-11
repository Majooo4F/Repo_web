package com.example.equipo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult()
          .getFieldErrors()
          .forEach(fe -> errores.put(fe.getField(), fe.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }
}