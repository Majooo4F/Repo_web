package com.example.equipo.service;

import com.example.equipo.dto.CursosDTO;
import com.example.equipo.entity.Cursos;
import com.example.equipo.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private static final int PAGE_SIZE = 8;

    private final CursoRepository cursoRepository;

    public Page<CursosDTO> listarCursosDisponibles(int page) {
        Pageable pageable = PageRequest.of(
                page,
                PAGE_SIZE,
                Sort.by(Sort.Direction.DESC, "fechaCreacion")
        );

        return cursoRepository.findByActivoTrue(pageable)
                .map(this::toDTO);
    }

    private CursosDTO toDTO(Cursos curso) {
        return new CursosDTO(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescripcion(),
                curso.getImagen(),
                curso.getCategoria(),
                curso.getDuracion(),
                curso.getAutor()
        );
    }
}