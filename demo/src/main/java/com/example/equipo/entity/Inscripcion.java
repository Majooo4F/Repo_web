package com.example.equipo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripcion")
@Data
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_curso", nullable = false)
    private Integer idCurso;

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion;

    @Column(nullable = false)
    private Integer estado;
}
