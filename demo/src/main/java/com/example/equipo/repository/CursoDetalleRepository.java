package com.example.equipo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.equipo.entity.Cursos;

@Repository
public interface CursoDetalleRepository extends JpaRepository<Cursos, Integer> {
    
}