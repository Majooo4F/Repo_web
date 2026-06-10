package com.example.equipo.repository;

import com.example.equipo.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Cursos, Integer> {
    // findById(Integer) ya viene incluido por JpaRepository.
}