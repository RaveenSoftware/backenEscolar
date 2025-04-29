package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Evaluacion;
import co.edu.udes.backend.repositories.EvaluacionRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    // Listar todas las evaluaciones
    public List<Evaluacion> listarTodas() {
        return evaluacionRepository.findAll();
    }

    // Buscar evaluación por ID
    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluación no existe con id: " + id));
    }

    // Crear evaluación
    public Evaluacion crear(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    // Actualizar evaluación
    public Evaluacion actualizar(Long id, Evaluacion nueva) {
        Evaluacion evaluacion = buscarPorId(id);
        evaluacion.setTipo(nueva.getTipo());
        evaluacion.setFecha(nueva.getFecha());
        evaluacion.setNota(nueva.getNota());
        evaluacion.setEstudiante(nueva.getEstudiante());
        evaluacion.setAsignatura(nueva.getAsignatura());
        evaluacion.setObservaciones(nueva.getObservaciones());
        return evaluacionRepository.save(evaluacion);
    }

    // Eliminar evaluación
    public void eliminar(Long id) {
        Evaluacion evaluacion = buscarPorId(id);
        evaluacionRepository.delete(evaluacion);
    }
}
