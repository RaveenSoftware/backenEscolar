package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.DocenteDTO;
import co.edu.udes.backend.models.Docente;
import org.springframework.stereotype.Component;

@Component
public class DocenteMapper {

    public DocenteDTO toDTO(Docente docente) {
        if (docente == null) {
            return null;
        }

        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setNombre(docente.getNombre());
        dto.setTelefono(docente.getTelefono());
        dto.setCorreoPersonal(docente.getCorreoPersonal());
        dto.setFechaNacimiento(docente.getFechaNacimiento());
        dto.setNumeroDocumento(docente.getNumeroDocumento());
        dto.setEstado(docente.isEstado());
        dto.setEspecialidad(docente.getEspecialidad());
        dto.setCodigoInstitucional(docente.getCodigoInstitucional());
        dto.setCorreoInstitucional(docente.getCorreoInstitucional());

        if (docente.getTipoDocumento() != null) {
            dto.setTipoDocumentoId(docente.getTipoDocumento().getId());
        }

        if (docente.getGenero() != null) {
            dto.setTipoGeneroId(docente.getGenero().getId());
        }

        if (docente.getFacultad() != null) {
            dto.setFacultadId(docente.getFacultad().getId());
        }

        if (docente.getCursos() != null) {
            dto.setCursosIds(docente.getCursos().stream()
                .map(curso -> curso.getId())
                .toList());
        }

        if (docente.getDisponibilidadHoraria() != null) {
            dto.setDisponibilidadHorariaIds(docente.getDisponibilidadHoraria().stream()
                .map(horario -> horario.getId())
                .toList());
        }

        return dto;
    }

    public Docente toEntity(DocenteDTO dto) {
        if (dto == null) {
            return null;
        }

        Docente docente = new Docente();
        docente.setId(dto.getId());
        docente.setNombre(dto.getNombre());
        docente.setTelefono(dto.getTelefono());
        docente.setCorreoPersonal(dto.getCorreoPersonal());
        docente.setFechaNacimiento(dto.getFechaNacimiento());
        docente.setNumeroDocumento(dto.getNumeroDocumento());
        docente.setEstado(dto.isEstado());
        docente.setEspecialidad(dto.getEspecialidad());
        docente.setCodigoInstitucional(dto.getCodigoInstitucional());
        docente.setCorreoInstitucional(dto.getCorreoInstitucional());

        return docente;
    }
}