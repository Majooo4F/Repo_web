package com.example.equipo.repository;

import com.example.equipo.entity.Cursos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CursoRepository extends JpaRepository<Cursos, Integer> {

    // Catálogo con paginación (HU03)
    Page<Cursos> findByActivoTrue(Pageable pageable);
}
