package co.edu.udes.backend.services;

import co.edu.udes.backend.dtos.EstudianteDTO;
import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ProgramaAcademicoRepository programaAcademicoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private TipoGeneroRepository tipoGeneroRepository;

    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no existe con ID: " + id));
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {
        if (estudianteRepository.findByNumeroDocumento(estudiante.getNumeroDocumento()).isPresent()) {
            throw new IllegalArgumentException("El número de documento ya está registrado.");
        }

        ProgramaAcademico programa = programaAcademicoRepository.findById(estudiante.getPrograma().getId())
                .orElseThrow(() -> new IllegalArgumentException("Programa académico no válido."));
        Rol rol = rolRepository.findById(estudiante.getRol().getId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido."));

        estudiante.setPrograma(programa);
        estudiante.setRol(rol);

        return estudianteRepository.save(estudiante);
    }

    public Estudiante actualizarEstudiante(Long id, Estudiante detalles) {
        Estudiante estudiante = obtenerPorId(id);

        estudiante.setNombre(detalles.getNombre());
        estudiante.setTelefono(detalles.getTelefono());
        estudiante.setCorreoPersonal(detalles.getCorreoPersonal());
        estudiante.setFechaNacimiento(detalles.getFechaNacimiento());
        estudiante.setNumeroDocumento(detalles.getNumeroDocumento());
        estudiante.setEstado(detalles.isEstado());
        estudiante.setCodigoInstitucional(detalles.getCodigoInstitucional());
        estudiante.setCorreoInstitucional(detalles.getCorreoInstitucional());

        if (detalles.getPrograma() != null) {
            ProgramaAcademico programa = programaAcademicoRepository.findById(detalles.getPrograma().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Programa académico no válido."));
            estudiante.setPrograma(programa);
        }

        if (detalles.getRol() != null) {
            Rol rol = rolRepository.findById(detalles.getRol().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no válido."));
            estudiante.setRol(rol);
        }

        return estudianteRepository.save(estudiante);
    }

    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = obtenerPorId(id);
        estudianteRepository.delete(estudiante);
    }

    public EstudianteDTO convertirADTO(Estudiante estudiante) {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setNombre(estudiante.getNombre());
        dto.setTelefono(estudiante.getTelefono());
        dto.setCorreoPersonal(estudiante.getCorreoPersonal());
        dto.setFechaNacimiento(estudiante.getFechaNacimiento());
        dto.setNumeroDocumento(estudiante.getNumeroDocumento());
        dto.setEstado(estudiante.isEstado());

        dto.setTipoDocumentoId(estudiante.getTipoDocumento().getId());
        dto.setTipoDocumentoNombre(estudiante.getTipoDocumento().getNombre());

        dto.setGeneroId(estudiante.getGenero().getId());
        dto.setGeneroNombre(estudiante.getGenero().getNombre());

        dto.setRolId(estudiante.getRol().getId());
        dto.setRolNombre(estudiante.getRol().getNombre());

        dto.setProgramaId(estudiante.getPrograma().getId());
        dto.setProgramaNombre(estudiante.getPrograma().getNombrePrograma());

        dto.setCodigoInstitucional(estudiante.getCodigoInstitucional());
        dto.setCorreoInstitucional(estudiante.getCorreoInstitucional());

        dto.setFacultadNombre(estudiante.getPrograma().getFacultad().getNombre());

        return dto;
    }

    public List<EstudianteDTO> obtenerTodosDTOs() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public Estudiante registrarEstudianteDesdeDTO(EstudianteDTO dto) {
        // Depuración temporal
        System.out.println("DTO recibido:");
        System.out.println("TipoDocumentoId: " + dto.getTipoDocumentoId());
        System.out.println("GeneroId: " + dto.getGeneroId());
        System.out.println("RolId: " + dto.getRolId());
        System.out.println("ProgramaId: " + dto.getProgramaId());

        // Validación inicial
        if (dto.getTipoDocumentoId() == null || dto.getGeneroId() == null || dto.getRolId() == null || dto.getProgramaId() == null) {
            throw new IllegalArgumentException("Uno o más IDs requeridos son nulos");
        }

        Estudiante estudiante = new Estudiante();

        estudiante.setNombre(dto.getNombre());
        estudiante.setTelefono(dto.getTelefono());
        estudiante.setCorreoPersonal(dto.getCorreoPersonal());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());
        estudiante.setNumeroDocumento(dto.getNumeroDocumento());
        estudiante.setEstado(dto.getEstado());
        estudiante.setCodigoInstitucional(dto.getCodigoInstitucional());
        estudiante.setCorreoInstitucional(dto.getCorreoInstitucional());

        estudiante.setTipoDocumento(
                tipoDocumentoRepository.findById(dto.getTipoDocumentoId())
                        .orElseThrow(() -> new IllegalArgumentException("Tipo de documento no válido"))
        );

        estudiante.setGenero(
                tipoGeneroRepository.findById(dto.getGeneroId())
                        .orElseThrow(() -> new IllegalArgumentException("Género no válido"))
        );

        estudiante.setRol(
                rolRepository.findById(dto.getRolId())
                        .orElseThrow(() -> new IllegalArgumentException("Rol no válido"))
        );

        ProgramaAcademico programa = programaAcademicoRepository.findById(dto.getProgramaId())
                .orElseThrow(() -> new IllegalArgumentException("Programa académico no válido"));
        estudiante.setPrograma(programa);

        return estudianteRepository.save(estudiante);
    }


}
