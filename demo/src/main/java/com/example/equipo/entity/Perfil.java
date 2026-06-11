package com.example.equipo.entity;

public class Perfil {
    private String nombre;
    private String email;
    private Integer edad;
    private String telefono;
    
    public Perfil() {}
    
    public Perfil(String nombre, String email, Integer edad, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
    }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}