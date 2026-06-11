package com.example.equipo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Datos que llegan al registrar un usuario (HU01).
 * Las anotaciones validan los criterios de aceptación antes de tocar la BD.
 */
@Data
public class RegistroDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    private String correo;

    // Mínimo 8 caracteres, al menos una mayúscula y un número
    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
        message = "La contraseña debe tener al menos 8 caracteres, una mayúscula y un número"
    )
    private String contrasena;

    // Opcional: si no se envía, el service asigna ESTUDIANTE por defecto.
    private String rol;
}