package com.example.equipo.service;

import com.example.equipo.dto.MisCursoDTO;
import com.example.equipo.entity.Cursos;
import com.example.equipo.entity.Inscripcion;
import com.example.equipo.entity.Usuarios;
import com.example.equipo.repository.CursoRepository;
import com.example.equipo.repository.InscripcionRepository;
import com.example.equipo.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Lógica de HU09: listar los cursos en los que el estudiante está inscrito.
 *
 * Como Inscripcion guarda idCurso (Integer) y NO una relación @ManyToOne,
 * por cada inscripción activa hay que cargar el curso desde CursoRepository.
 */
@Service
public class MisCursosService {

    private static final int ESTADO_ACTIVA = 1;

    private final UsuariosRepository usuariosRepository;
    private final InscripcionRepository inscripcionRepository;
    private final CursoRepository cursoRepository;

    public MisCursosService(UsuariosRepository usuariosRepository,
                            InscripcionRepository inscripcionRepository,
                            CursoRepository cursoRepository) {
        this.usuariosRepository = usuariosRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.cursoRepository = cursoRepository;
    }

    /**
     * Cursos activos del usuario identificado por su correo (sesión).
     * Si no tiene inscripciones, la lista viene vacía: el frontend
     * muestra entonces el botón "Explorar catálogo".
     */
    @Transactional(readOnly = true)
    public List<MisCursoDTO> obtenerMisCursos(String correo) {
        Usuarios usuario = usuariosRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + correo));

        List<Inscripcion> inscripciones =
                inscripcionRepository.findByIdUsuarioAndEstado(usuario.getId(), ESTADO_ACTIVA);

        List<MisCursoDTO> resultado = new ArrayList<>();
        for (Inscripcion inscripcion : inscripciones) {
            cursoRepository.findById(inscripcion.getIdCurso())
                    .ifPresent(curso -> resultado.add(aDTO(curso, inscripcion)));
        }
        return resultado;
    }

    private MisCursoDTO aDTO(Cursos curso, Inscripcion inscripcion) {
        return MisCursoDTO.builder()
                .cursoId(curso.getId())
                .titulo(curso.getTitulo())
                .descripcion(curso.getDescripcion())
                .imagen(curso.getImagen())
                .categoria(curso.getCategoria())
                .duracion(curso.getDuracion())
                .autor(curso.getAutor())
                .fechaInscripcion(inscripcion.getFechaInscripcion())
                .build();
    }
}