package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.CursoDTO;
import co.edu.udes.backend.models.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }

        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setCodigo(curso.getCodigo());
        dto.setCreditos(curso.getCreditos());
        dto.setCupoMaximo(curso.getCupoMaximo());
        dto.setInscritosActuales(curso.getInscritosActuales());
        dto.setContenido(curso.getContenido());
        dto.setObjetivos(curso.getObjetivos());
        dto.setCompetencias(curso.getCompetencias());

        if (curso.getPrograma() != null) {
            dto.setProgramaId(curso.getPrograma().getId());
        }
        
        if (curso.getAsignatura() != null) {
            dto.setAsignaturaId(curso.getAsignatura().getId());
        }
        
        if (curso.getHorario() != null) {
            dto.setHorarioId(curso.getHorario().getId());
        }

        if (curso.getDocentes() != null) {
            dto.setDocentesIds(curso.getDocentes().stream()
                .map(docente -> docente.getId())
                .toList());
        }

        if (curso.getPrerrequisitos() != null) {
            dto.setPrerequisitosIds(curso.getPrerrequisitos().stream()
                .map(prereq -> prereq.getId())
                .toList());
        }

        return dto;
    }

    public Curso toEntity(CursoDTO dto) {
        if (dto == null) {
            return null;
        }

        Curso curso = new Curso();
        curso.setId(dto.getId());
        curso.setNombre(dto.getNombre());
        curso.setCodigo(dto.getCodigo());
        curso.setCreditos(dto.getCreditos());
        curso.setCupoMaximo(dto.getCupoMaximo());
        curso.setInscritosActuales(dto.getInscritosActuales());
        curso.setContenido(dto.getContenido());
        curso.setObjetivos(dto.getObjetivos());
        curso.setCompetencias(dto.getCompetencias());

        return curso;
    }
}