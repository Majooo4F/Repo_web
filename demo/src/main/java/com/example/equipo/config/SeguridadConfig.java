package com.example.equipo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Provee el codificador de contraseñas (BCrypt).
 *
 * Usa solo 'spring-security-crypto' (la utilidad de hashing), NO el
 * starter completo de seguridad, por eso NO bloquea los endpoints.
 * Cuando se integre HU02 con el login real, aquí se añadirá la
 * configuración del filtro de seguridad.
 */
@Configuration
public class SeguridadConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}