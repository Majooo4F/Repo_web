package com.example.equipo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursosDTO {

    private Integer id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede superar 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 255, message = "La categoría no puede superar 255 caracteres")
    private String categoria;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser al menos 1 hora")
    @Max(value = 9999, message = "La duración no puede superar 9999 horas")
    private Integer duracion;

    @NotBlank(message = "El nombre del profesor es obligatorio")
    @Size(max = 100, message = "El nombre del autor no puede superar 100 caracteres")
    private String autor;

    private String imagen;
}