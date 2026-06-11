package com.example.equipo.service;

import com.example.equipo.entity.Cursos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoDetalleService {
    private final List<Cursos> listaCursosSimulada = new ArrayList<>();

    public CursoDetalleService() {
        Cursos curso1 = new Cursos();
        curso1.setId(1);
        curso1.setTitulo("Desarrollo Web con Spring Boot");
        curso1.setDescripcion("Curso completo para aprender a desarrollar APIs REST robustas y escalables.");
        curso1.setImagen("springboot-portada.png");
        curso1.setCategoria("Programación");
        curso1.setFechaInicio(LocalDate.of(2026, 6, 15));
        curso1.setFechaFin(LocalDate.of(2026, 7, 20));
        curso1.setActivo(true);
        curso1.setDuracion(40);
        curso1.setAutor("Jonathan Cruz");
        curso1.setFechaCreacion(LocalDateTime.now());

        Cursos curso2 = new Cursos();
        curso2.setId(2);
        curso2.setTitulo("Maquetación y Frontend con React");
        curso2.setDescripcion("Domina componentes, hooks y consumo de APIs REST de forma interactiva.");
        curso2.setImagen("react-portada.png");
        curso2.setCategoria("Diseño Web");
        curso2.setFechaInicio(LocalDate.of(2026, 7, 1));
        curso2.setFechaFin(LocalDate.of(2026, 8, 5));
        curso2.setActivo(true);
        curso2.setDuracion(32);
        curso2.setAutor("Equipo IDGS15");
        curso2.setFechaCreacion(LocalDateTime.now());

        listaCursosSimulada.add(curso1);
        listaCursosSimulada.add(curso2);
    }

    public Optional<Cursos> obtenerCursoPorId(Integer id) {
        return listaCursosSimulada.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst();
    }
}