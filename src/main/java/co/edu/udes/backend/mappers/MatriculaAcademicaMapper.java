package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.MatriculaAcademicaDTO;
import co.edu.udes.backend.models.MatriculaAcademica;
import org.springframework.stereotype.Component;

@Component
public class MatriculaAcademicaMapper {

    public MatriculaAcademicaDTO toDTO(MatriculaAcademica matricula) {
        if (matricula == null) {
            return null;
        }

        MatriculaAcademicaDTO dto = new MatriculaAcademicaDTO();
        dto.setId(matricula.getId());
        dto.setCodigo(matricula.getCodigo());
        dto.setFecha(matricula.getFecha());
        dto.setEstado(matricula.isEstado());
        dto.setSemestre(matricula.getSemestre());
        dto.setCreditosActuales(matricula.getCreditosActuales());

        if (matricula.getEstudiante() != null) {
            dto.setEstudianteId(matricula.getEstudiante().getId());
        }

        if (matricula.getCursos() != null) {
            dto.setCursosIds(matricula.getCursos().stream()
                .map(curso -> curso.getId())
                .toList());
        }

        return dto;
    }

    public MatriculaAcademica toEntity(MatriculaAcademicaDTO dto) {
        if (dto == null) {
            return null;
        }

        MatriculaAcademica matricula = new MatriculaAcademica();
        matricula.setId(dto.getId());
        matricula.setCodigo(dto.getCodigo());
        matricula.setFecha(dto.getFecha());
        matricula.setEstado(dto.isEstado());
        matricula.setSemestre(dto.getSemestre());
        matricula.setCreditosActuales(dto.getCreditosActuales());

        return matricula;
    }
}