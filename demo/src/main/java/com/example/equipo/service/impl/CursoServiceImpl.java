package com.example.equipo.service.impl;

import com.example.equipo.dto.CursoDTO;
import com.example.equipo.entity.Cursos;
import com.example.equipo.repository.CursoRepository;
import com.example.equipo.service.CursoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getActivo()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CursoDTO obtenerPorId(Integer id) {
        return toDTO(buscarOLanzar(id));
    }

    @Override
    @Transactional
    public CursoDTO crear(CursoDTO dto) {
        Cursos curso = toEntity(dto);
        curso.setActivo(true);
        curso.setFechaCreacion(LocalDateTime.now());
        if (curso.getImagen() == null || curso.getImagen().isBlank()) {
            curso.setImagen("default-course.png");
        }
        return toDTO(cursoRepository.save(curso));
    }

    @Override
    @Transactional
    public CursoDTO actualizar(Integer id, CursoDTO dto) {
        Cursos curso = buscarOLanzar(id);
        curso.setTitulo(dto.getTitulo());
        curso.setDescripcion(dto.getDescripcion());
        curso.setCategoria(dto.getCategoria());
        curso.setDuracion(dto.getDuracion());
        curso.setAutor(dto.getAutor());
        if (dto.getImagen() != null && !dto.getImagen().isBlank()) {
            curso.setImagen(dto.getImagen());
        }
        return toDTO(cursoRepository.save(curso));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        Cursos curso = buscarOLanzar(id);
        curso.setActivo(false);   // soft-delete: desaparece del catálogo
        cursoRepository.save(curso);
    }

    // ── helpers ───────────────────────────────────────────────────────────────

    private Cursos buscarOLanzar(Integer id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Curso no encontrado con id: " + id));
    }

    private CursoDTO toDTO(Cursos c) {
        CursoDTO dto = new CursoDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setDescripcion(c.getDescripcion());
        dto.setCategoria(c.getCategoria());
        dto.setDuracion(c.getDuracion());
        dto.setAutor(c.getAutor());
        dto.setImagen(c.getImagen());
        return dto;
    }

    private Cursos toEntity(CursoDTO dto) {
        Cursos c = new Cursos();
        c.setTitulo(dto.getTitulo());
        c.setDescripcion(dto.getDescripcion());
        c.setCategoria(dto.getCategoria());
        c.setDuracion(dto.getDuracion());
        c.setAutor(dto.getAutor());
        c.setImagen(dto.getImagen());
        return c;
    }
}