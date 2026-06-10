package com.example.equipo.controller;

import com.example.equipo.dto.CursoDTO;
import com.example.equipo.service.CursoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CursosController {

    private final CursoService cursoService;

    // GET /api/cursos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    // GET /api/cursos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(cursoService.obtenerPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // POST /api/cursos  — @Valid activa las validaciones del DTO
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoService.crear(cursoDTO));
    }

    // PUT /api/cursos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @Valid @RequestBody CursoDTO cursoDTO) {
        try {
            return ResponseEntity.ok(cursoService.actualizar(id, cursoDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE /api/cursos/{id}  — soft-delete, el frontend pide confirmación antes
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            cursoService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Curso eliminado correctamente"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}