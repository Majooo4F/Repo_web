package com.example.equipo.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cursos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(length = 255, nullable = false)
    private String imagen;

    @Column(length = 255, nullable = false)
    private String categoria;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    private Boolean activo;

    private Integer duracion;

    @Column(nullable = false)
    private String autor;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}
