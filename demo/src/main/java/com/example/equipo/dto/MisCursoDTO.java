package com.example.equipo.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Datos de cada tarjeta del panel "Mis Cursos" (HU09).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MisCursoDTO {
    private Integer cursoId;
    private String titulo;
    private String descripcion;
    private String imagen;
    private String categoria;
    private Integer duracion;
    private String autor;
    private LocalDateTime fechaInscripcion;
}