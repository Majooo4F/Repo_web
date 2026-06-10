package com.example.equipo.service;

import com.example.equipo.dto.CursoDTO;
import java.util.List;

public interface CursoService {
    List<CursoDTO> listarTodos();
    CursoDTO obtenerPorId(Integer id);
    CursoDTO crear(CursoDTO cursoDTO);
    CursoDTO actualizar(Integer id, CursoDTO cursoDTO);
    void eliminar(Integer id);
}