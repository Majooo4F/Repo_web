package com.example.equipo.service;

import com.example.equipo.entity.Perfil;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PerfilService {
    
    private Map<String, Perfil> perfiles = new HashMap<>();
    private String usuarioActual = "user1"; 

    //ejemplo nomas
    public PerfilService() {
        Perfil perfilDefault = new Perfil("Juanazo", "juanazo69@email.com", 30, "555-1234");
        perfiles.put(usuarioActual, perfilDefault);
    }
    
    public Perfil obtenerPerfil() {
        return perfiles.get(usuarioActual);
    }
    
    public Perfil actualizarPerfil(Perfil nuevoPerfil) {
        perfiles.put(usuarioActual, nuevoPerfil);
        return nuevoPerfil;
    }
    
    public Perfil actualizarNombre(String nombre) {
        Perfil perfil = obtenerPerfil();
        perfil.setNombre(nombre);
        return actualizarPerfil(perfil);
    }
    
    public Perfil actualizarEmail(String email) {
        Perfil perfil = obtenerPerfil();
        perfil.setEmail(email);
        return actualizarPerfil(perfil);
    }
}