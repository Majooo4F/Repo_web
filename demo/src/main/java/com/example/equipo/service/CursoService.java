package com.example.equipo.service;

import com.example.equipo.dto.CursosDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CursoService {
    List<CursosDTO> listarTodos();
    CursosDTO obtenerPorId(Integer id);
    CursosDTO crear(CursosDTO cursoDTO);
    CursosDTO actualizar(Integer id, CursosDTO cursoDTO);
    void eliminar(Integer id);

    Page<CursosDTO> listarCursosDisponibles(int page);
}