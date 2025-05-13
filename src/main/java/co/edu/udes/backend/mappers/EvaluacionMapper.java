package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.EvaluacionDTO;
import co.edu.udes.backend.models.Evaluacion;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionMapper {

    public EvaluacionDTO toDTO(Evaluacion evaluacion) {
        if (evaluacion == null) {
            return null;
        }

        EvaluacionDTO dto = new EvaluacionDTO();
        dto.setId(evaluacion.getId());
        dto.setTipo(evaluacion.getTipo());
        dto.setFecha(evaluacion.getFecha());
        dto.setNota(evaluacion.getNota());
        dto.setObservaciones(evaluacion.getObservaciones());

        if (evaluacion.getEstudiante() != null) {
            dto.setEstudianteId(evaluacion.getEstudiante().getId());
        }

        if (evaluacion.getAsignatura() != null) {
            dto.setAsignaturaId(evaluacion.getAsignatura().getId());
        }

        return dto;
    }

    public Evaluacion toEntity(EvaluacionDTO dto) {
        if (dto == null) {
            return null;
        }

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(dto.getId());
        evaluacion.setTipo(dto.getTipo());
        evaluacion.setFecha(dto.getFecha());
        evaluacion.setNota(dto.getNota());
        evaluacion.setObservaciones(dto.getObservaciones());

        return evaluacion;
    }
}