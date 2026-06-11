package com.example.equipo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.equipo.entity.Cursos;

public interface CursoRepository extends JpaRepository<Cursos, Integer> {

}