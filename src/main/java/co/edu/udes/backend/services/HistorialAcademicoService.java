package co.edu.udes.backend.services;

import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistorialAcademicoService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaHorarioRepository aulaHorarioRepository;

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Autowired
    private PoligrafoRepository poligrafoRepository;

    // 1. Ver el horario de un estudiante (basado en sus cursos)
    public List<AulaHorario> obtenerHorarioEstudiante(Long estudianteId) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(estudianteId);
        if (estudianteOpt.isEmpty()) return Collections.emptyList();

        Estudiante estudiante = estudianteOpt.get();

        List<Curso> cursos = cursoRepository.findByMatriculaAcademicaEstudiante(estudiante);

        List<AulaHorario> horarios = new ArrayList<>();
        for (Curso curso : cursos) {
            horarios.addAll(aulaHorarioRepository.findByCurso(curso));
        }
        return horarios;
    }

    // 2. Ver las notas puestas por un docente
    public List<Calificaciones> obtenerNotasPorDocente(Long docenteId) {
        return calificacionesRepository.findByCurso_Docente_Id(docenteId);
    }

    // 3. Ver la nota final de un estudiante en un curso
    public Optional<Double> obtenerNotaFinalCurso(Long estudianteId, Long cursoId) {
        return calificacionesRepository.findByEstudianteIdAndCursoId(estudianteId, cursoId)
                .map(Calificaciones::getDefinitiva);
    }

    // 4. Ver todas las notas finales de un estudiante
    public List<Double> obtenerNotasFinalesEstudiante(Long estudianteId) {
        return calificacionesRepository.findByEstudianteId(estudianteId)
                .stream()
                .map(Calificaciones::getDefinitiva)
                .collect(Collectors.toList());
    }
}
