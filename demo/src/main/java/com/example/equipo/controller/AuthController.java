package com.example.equipo.controller;

import com.example.equipo.dto.RegistroDTO;
import com.example.equipo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Endpoint de autenticación. Por ahora solo el registro (HU01).
 * El login (HU02) se agregará aquí mismo más adelante.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registra un usuario. Si los datos son válidos y el correo no existe,
     * responde 201 con un mensaje de confirmación (el frontend redirige al login).
     */
    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registro(@Valid @RequestBody RegistroDTO dto) {
        authService.registrar(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensaje", "Registro exitoso. Ya puedes iniciar sesión."));
    }
}