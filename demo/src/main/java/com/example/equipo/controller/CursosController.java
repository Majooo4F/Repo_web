package com.example.equipo.controller;

import com.example.equipo.dto.CursosDTO;
import com.example.equipo.service.CursoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CursosController {

    private final CursoService cursoService;

    // ── Tu rama (CRUD) ────────────────────────────────────────────────────────

    @GetMapping("/api/cursos/listar")
    public ResponseEntity<List<CursosDTO>> listar() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/api/cursos/buscar/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(cursoService.obtenerPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/api/cursos/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody CursosDTO cursoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoService.crear(cursoDTO));
    }

    @PutMapping("/api/cursos/editar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @Valid @RequestBody CursosDTO cursoDTO) {
        try {
            return ResponseEntity.ok(cursoService.actualizar(id, cursoDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/api/cursos/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            cursoService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Curso eliminado correctamente"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // ── Rama del compañero (catálogo paginado) ────────────────────────────────

    @GetMapping("/api/cursos/catalogo")
    public ResponseEntity<Page<CursosDTO>> getCatalogo(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(cursoService.listarCursosDisponibles(page));
    }
}