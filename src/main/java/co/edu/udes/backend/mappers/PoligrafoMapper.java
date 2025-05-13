package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.PoligrafoDTO;
import co.edu.udes.backend.models.Poligrafo;
import org.springframework.stereotype.Component;

@Component
public class PoligrafoMapper {

    public PoligrafoDTO toDTO(Poligrafo poligrafo) {
        if (poligrafo == null) {
            return null;
        }

        PoligrafoDTO dto = new PoligrafoDTO();
        dto.setId(poligrafo.getId());
        dto.setFechaEmision(poligrafo.getFechaEmision());
        dto.setCreditosMatriculados(poligrafo.getCreditosMatriculados());
        dto.setPromedio(poligrafo.getPromedio());
        dto.setCreditosAcumulados(poligrafo.getCreditosAcumulados());
        dto.setPromedioAcumulado(poligrafo.getPromedioAcumulado());

        if (poligrafo.getEstudiante() != null) {
            dto.setEstudianteId(poligrafo.getEstudiante().getId());
        }

        if (poligrafo.getAsignatura() != null) {
            dto.setAsignaturaId(poligrafo.getAsignatura().getId());
        }

        if (poligrafo.getCalificaciones() != null) {
            dto.setCalificacionesId(poligrafo.getCalificaciones().getId());
        }

        if (poligrafo.getSemestreAcademico() != null) {
            dto.setSemestreAcademicoId(poligrafo.getSemestreAcademico().getId());
        }

        return dto;
    }

    public Poligrafo toEntity(PoligrafoDTO dto) {
        if (dto == null) {
            return null;
        }

        Poligrafo poligrafo = new Poligrafo();
        poligrafo.setId(dto.getId());
        poligrafo.setFechaEmision(dto.getFechaEmision());
        poligrafo.setCreditosMatriculados(dto.getCreditosMatriculados());
        poligrafo.setPromedio(dto.getPromedio());
        poligrafo.setCreditosAcumulados(dto.getCreditosAcumulados());
        poligrafo.setPromedioAcumulado(dto.getPromedioAcumulado());

        return poligrafo;
    }
}