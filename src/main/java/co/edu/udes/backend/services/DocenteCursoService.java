package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.models.Horario;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.DocenteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocenteCursoService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Docente asignarCurso(Long docenteId, Long cursoId) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + docenteId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        if (docente.getCursos() != null && docente.getCursos().contains(curso)) {
            throw new IllegalStateException("El curso ya está asignado a este docente.");
        }

        // Validar disponibilidad horaria
        if (!docente.getDisponibilidadHoraria().contains(curso.getHorario())) {
            throw new IllegalStateException("El docente no está disponible en el horario del curso.");
        }

        // Validar traslape con otros cursos del docente
        for (Curso existente : docente.getCursos()) {
            Horario h1 = existente.getHorario();
            Horario h2 = curso.getHorario();
            if (h1.getDia().equalsIgnoreCase(h2.getDia()) &&
                    h1.getHoraInicio().isBefore(h2.getHoraFinalizacion()) &&
                    h2.getHoraInicio().isBefore(h1.getHoraFinalizacion())) {
                throw new IllegalStateException("Este curso se traslapa con otro curso ya asignado al docente.");
            }
        }

        // Validar carga horaria máxima
        int totalHoras = docente.getCursos().stream()
                .mapToInt(c -> c.getHorario().getDuracionHoras())
                .sum();

        int horasCurso = curso.getHorario().getDuracionHoras();
        if ((totalHoras + horasCurso) > 20) {
            throw new IllegalStateException("El docente excede el máximo de 20 horas semanales.");
        }

        docente.getCursos().add(curso);
        return docenteRepository.save(docente);
    }

    public Set<Curso> obtenerCursosPorDocente(Long docenteId) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + docenteId));
        return docente.getCursos();
    }

    public Docente eliminarCurso(Long docenteId, Long cursoId) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + docenteId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        docente.getCursos().remove(curso);
        return docenteRepository.save(docente);
    }
}
