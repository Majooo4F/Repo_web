package com.example.equipo.controller;

import com.example.equipo.dto.CursosDTO;
import com.example.equipo.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CursosController {

    private final CursoService cursoService;


    @GetMapping("/catalogo")
public ResponseEntity<Page<CursosDTO>> getCatalogo(
        @RequestParam(defaultValue = "0") int page) {

    Page<CursosDTO> resultado = cursoService.listarCursosDisponibles(page);
    return ResponseEntity.ok(resultado);
}
}