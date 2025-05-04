package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Evaluacion;
import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.repositories.EvaluacionRepository;
import co.edu.udes.backend.repositories.AsignaturaRepository;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // Crear una nueva evaluación
    public Evaluacion crearEvaluacion(Evaluacion evaluacion) {
        Long estudianteId = evaluacion.getEstudiante().getId();
        Long asignaturaId = evaluacion.getAsignatura().getId();

        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + estudianteId));
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada con ID: " + asignaturaId));

        evaluacion.setEstudiante(estudiante);
        evaluacion.setAsignatura(asignatura);

        return evaluacionRepository.save(evaluacion);
    }

    // Obtener todas las evaluaciones
    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    // Obtener evaluación por ID
    public Evaluacion obtenerPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluación no encontrada con ID: " + id));
    }

    // Actualizar evaluación
    public Evaluacion actualizarEvaluacion(Long id, Evaluacion detalles) {
        Evaluacion evaluacion = obtenerPorId(id);

        evaluacion.setTipo(detalles.getTipo());
        evaluacion.setFecha(detalles.getFecha());
        evaluacion.setNota(detalles.getNota());
        evaluacion.setObservaciones(detalles.getObservaciones());

        // Reasignar estudiante o asignatura si se quiere
        if (detalles.getEstudiante() != null) {
            Long estudianteId = detalles.getEstudiante().getId();
            Estudiante estudiante = estudianteRepository.findById(estudianteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + estudianteId));
            evaluacion.setEstudiante(estudiante);
        }

        if (detalles.getAsignatura() != null) {
            Long asignaturaId = detalles.getAsignatura().getId();
            Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada con ID: " + asignaturaId));
            evaluacion.setAsignatura(asignatura);
        }

        return evaluacionRepository.save(evaluacion);
    }

    // Eliminar evaluación
    public void eliminarEvaluacion(Long id) {
        Evaluacion evaluacion = obtenerPorId(id);
        evaluacionRepository.delete(evaluacion);
    }
}
