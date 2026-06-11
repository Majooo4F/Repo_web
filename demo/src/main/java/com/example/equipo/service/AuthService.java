package com.example.equipo.service;

import com.example.equipo.dto.RegistroDTO;
import com.example.equipo.entity.Usuarios;
import com.example.equipo.repository.UsuariosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Lógica de registro de usuarios (HU01).
 */
@Service
public class AuthService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuariosRepository usuariosRepository,
                       PasswordEncoder passwordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra un nuevo usuario.
     * - Verifica que el correo no exista (criterio: correo único).
     * - Encripta la contraseña con BCrypt.
     * - Asigna ESTUDIANTE si no se indicó rol.
     */
    public Usuarios registrar(RegistroDTO dto) {
        if (usuariosRepository.existsByCorreo(dto.getCorreo())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El correo ya está registrado");
        }

        Usuarios usuario = new Usuarios();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        usuario.setRol(dto.getRol() != null ? dto.getRol() : "ESTUDIANTE");
        usuario.setActivo(true);

        return usuariosRepository.save(usuario);
    }
}