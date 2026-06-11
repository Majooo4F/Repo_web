package com.example.equipo.controller;

import com.example.equipo.entity.Cursos;
import com.example.equipo.service.CursoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Permite que el Frontend se conecte sin problemas de CORS
public class CursoDetalleController {

    @Autowired
    private CursoDetalleService cursoDetalleService;

    // Endpoint GET para traer la información completa del curso por su ID
    @GetMapping("/curso-detalle/{id}")
    public ResponseEntity<Cursos> verDetalleCurso(@PathVariable Integer id) {
        return cursoDetalleService.obtenerCursoPorId(id)
                .map(curso -> ResponseEntity.ok().body(curso))
                .orElse(ResponseEntity.notFound().build());
    }
}