package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.repositories.CalificacionesRepository;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionesService {

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Calificaciones registrarCalificacion(Calificaciones calificacion) {
        // Verifica existencia de estudiante y curso
        Estudiante estudiante = estudianteRepository.findById(calificacion.getEstudiante().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        Curso curso = cursoRepository.findById(calificacion.getCurso().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        // Asigna y calcula
        calificacion.setEstudiante(estudiante);
        calificacion.setCurso(curso);
        double definitiva = calcularAcumulado(calificacion.getP1(), calificacion.getP2(), calificacion.getP3());
        calificacion.setDefinitiva(definitiva);

        return calificacionesRepository.save(calificacion);
    }

    public Calificaciones actualizarCalificacion(Long id, Calificaciones nueva) {
        Calificaciones existente = calificacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación no encontrada con ID: " + id));

        existente.setP1(nueva.getP1());
        existente.setP2(nueva.getP2());
        existente.setP3(nueva.getP3());

        double definitiva = calcularAcumulado(nueva.getP1(), nueva.getP2(), nueva.getP3());
        existente.setDefinitiva(definitiva);

        return calificacionesRepository.save(existente);
    }

    public List<Calificaciones> obtenerTodas() {
        return calificacionesRepository.findAll();
    }

    public Calificaciones obtenerPorId(Long id) {
        return calificacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        Calificaciones existente = obtenerPorId(id);
        calificacionesRepository.delete(existente);
    }

    private double calcularAcumulado(Double p1, Double p2, Double p3) {
        if (p1 == null) p1 = 0.0;
        if (p2 == null) p2 = 0.0;
        if (p3 == null) p3 = 0.0;
        return Math.round((p1 * 0.3 + p2 * 0.3 + p3 * 0.4) * 100.0) / 100.0;
    }
}
