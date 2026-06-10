package com.example.equipo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursosDTO {

    private Integer id;
    private String titulo;
    private String descripcion;
    private String imagen;
    private String categoria;
    private Integer duracion;
    private String autor;
}