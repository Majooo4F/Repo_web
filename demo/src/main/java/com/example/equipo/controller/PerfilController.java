package com.example.equipo.controller;

import com.example.equipo.entity.Perfil;
import com.example.equipo.service.PerfilService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {
    
    private final PerfilService perfilService;
    
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }
    
    @GetMapping
    public Perfil obtenerPerfil() {
        return perfilService.obtenerPerfil();
    }
    
    @PutMapping
    public Perfil actualizarPerfil(@RequestBody Perfil perfil) {
        return perfilService.actualizarPerfil(perfil);
    }
    
    @PatchMapping("/nombre")
    public Map<String, String> actualizarNombre(@RequestBody Map<String, String> request) {
        String nombre = request.get("nombre");
        perfilService.actualizarNombre(nombre);
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Nombre actualizado correctamente");
        respuesta.put("nombre", nombre);
        return respuesta;
    }
    
    @PatchMapping("/email")
    public Map<String, String> actualizarEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        perfilService.actualizarEmail(email);
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Email actualizado correctamente");
        respuesta.put("email", email);
        return respuesta;
    }
}