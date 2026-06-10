package com.example.equipo.repository;

import com.example.equipo.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    // Identifica al usuario logueado a partir de su correo (sesión).
    // Resuelve contra la propiedad 'Correo' (getCorreo) de la entidad.
    Optional<Usuarios> findByCorreo(String correo);
}