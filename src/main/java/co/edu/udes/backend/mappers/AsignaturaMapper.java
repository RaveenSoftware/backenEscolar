package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AsignaturaDTO;
import co.edu.udes.backend.models.Asignatura;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaMapper {

    public AsignaturaDTO toDTO(Asignatura asignatura) {
        if (asignatura == null) {
            return null;
        }

        AsignaturaDTO dto = new AsignaturaDTO();
        dto.setId(asignatura.getId());
        dto.setCodigo(asignatura.getCodigo());
        dto.setNombre(asignatura.getNombre());
        dto.setNumeroCreditos(asignatura.getNumeroCreditos());
        dto.setNumeroSemestre(asignatura.getNumeroSemestre());
        
        if (asignatura.getPensum() != null) {
            dto.setPensumId(asignatura.getPensum().getId());
        }
        
        if (asignatura.getPredecesora() != null) {
            dto.setPredecesoraId(asignatura.getPredecesora().getId());
        }

        return dto;
    }

    public Asignatura toEntity(AsignaturaDTO dto) {
        if (dto == null) {
            return null;
        }

        Asignatura asignatura = new Asignatura();
        asignatura.setId(dto.getId());
        asignatura.setCodigo(dto.getCodigo());
        asignatura.setNombre(dto.getNombre());
        asignatura.setNumeroCreditos(dto.getNumeroCreditos());
        asignatura.setNumeroSemestre(dto.getNumeroSemestre());
        
        return asignatura;
    }
}