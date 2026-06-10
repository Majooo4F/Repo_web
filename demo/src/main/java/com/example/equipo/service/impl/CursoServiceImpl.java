package com.example.equipo.service.impl;

import com.example.equipo.dto.CursosDTO;
import com.example.equipo.entity.Cursos;
import com.example.equipo.repository.CursoRepository;
import com.example.equipo.service.CursoService;
import org.springframework.data.domain.Page;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private static final int PAGE_SIZE = 8;

    @Override
    @Transactional(readOnly = true)
    public Page<CursosDTO> listarCursosDisponibles(int page) {
        Pageable pageable = PageRequest.of(
                page,
                PAGE_SIZE,
                Sort.by(Sort.Direction.DESC, "fechaCreacion")
        );
        return cursoRepository.findByActivoTrue(pageable)
                .map(this::toCursosDTO);
    }

    private CursosDTO toCursosDTO(Cursos c) {
        return new CursosDTO(
                c.getId(),
                c.getTitulo(),
                c.getDescripcion(),
                c.getCategoria(),   // ← categoria antes que imagen
                c.getDuracion(),
                c.getAutor(),
                c.getImagen()       // ← imagen al final
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursosDTO> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getActivo()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CursosDTO obtenerPorId(Integer id) {
        return toDTO(buscarOLanzar(id));
    }

    @Override
    @Transactional
    public CursosDTO crear(CursosDTO dto) {
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
    public CursosDTO actualizar(Integer id, CursosDTO dto) {
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
        curso.setActivo(false);
        cursoRepository.save(curso);
    }

    private Cursos buscarOLanzar(Integer id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Curso no encontrado con id: " + id));
    }

    private CursosDTO toDTO(Cursos c) {
        CursosDTO dto = new CursosDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setDescripcion(c.getDescripcion());
        dto.setCategoria(c.getCategoria());
        dto.setDuracion(c.getDuracion());
        dto.setAutor(c.getAutor());
        dto.setImagen(c.getImagen());
        return dto;
    }

    private Cursos toEntity(CursosDTO dto) {
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