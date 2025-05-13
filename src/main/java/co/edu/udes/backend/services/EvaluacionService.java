package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.EvaluacionDTO;
import co.edu.udes.backend.mappers.EvaluacionMapper;
import co.edu.udes.backend.models.Evaluacion;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private EvaluacionMapper evaluacionMapper;

    public EvaluacionDTO crearEvaluacion(EvaluacionDTO evaluacionDTO) {
        Evaluacion evaluacion = evaluacionMapper.toEntity(evaluacionDTO);
        
        if (evaluacionDTO.getEstudianteId() != null) {
            evaluacion.setEstudiante(estudianteRepository.findById(evaluacionDTO.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado")));
        }
        
        if (evaluacionDTO.getAsignaturaId() != null) {
            evaluacion.setAsignatura(asignaturaRepository.findById(evaluacionDTO.getAsignaturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada")));
        }

        return evaluacionMapper.toDTO(evaluacionRepository.save(evaluacion));
    }

    public List<EvaluacionDTO> listarEvaluaciones() {
        return evaluacionRepository.findAll().stream()
            .map(evaluacionMapper::toDTO)
            .collect(Collectors.toList());
    }

    public EvaluacionDTO obtenerPorId(Long id) {
        return evaluacionMapper.toDTO(evaluacionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evaluación no encontrada")));
    }

    public EvaluacionDTO actualizarEvaluacion(Long id, EvaluacionDTO evaluacionDTO) {
        Evaluacion existente = evaluacionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evaluación no encontrada"));

        Evaluacion actualizada = evaluacionMapper.toEntity(evaluacionDTO);
        actualizada.setId(existente.getId());
        
        if (evaluacionDTO.getEstudianteId() != null) {
            actualizada.setEstudiante(estudianteRepository.findById(evaluacionDTO.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado")));
        }

        if (evaluacionDTO.getAsignaturaId() != null) {
            actualizada.setAsignatura(asignaturaRepository.findById(evaluacionDTO.getAsignaturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada")));
        }

        return evaluacionMapper.toDTO(evaluacionRepository.save(actualizada));
    }

    public void eliminarEvaluacion(Long id) {
        if (!evaluacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evaluación no encontrada");
        }
        evaluacionRepository.deleteById(id);
    }
}