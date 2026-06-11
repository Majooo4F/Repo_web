package com.example.equipo.repository;

import com.example.equipo.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    /**
     * Inscripciones de un usuario filtradas por estado.
     * Para HU09 se llama con estado = 1 (solo cursos activos, sin las bajas).
     * Usa los campos idUsuario y estado de la entidad Inscripcion.
     */
    List<Inscripcion> findByIdUsuarioAndEstado(Integer idUsuario, Integer estado);
}