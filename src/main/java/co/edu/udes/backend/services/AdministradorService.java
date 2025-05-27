package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Administrador;
import co.edu.udes.backend.models.Facultad;
import co.edu.udes.backend.models.Rol;
import co.edu.udes.backend.repositories.AdministradorRepository;
import co.edu.udes.backend.repositories.FacultadRepository;
import co.edu.udes.backend.repositories.RolRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.udes.backend.dtos.AdministradorDTO;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private RolRepository rolRepository;

    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    public Administrador obtenerPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID: " + id));
    }

    public Administrador registrarAdministrador(Administrador admin) {
        Rol rol = rolRepository.findById(admin.getRol().getId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido."));
        Facultad facultad = facultadRepository.findById(admin.getFacultad().getId())
                .orElseThrow(() -> new IllegalArgumentException("Facultad no válida."));

        admin.setRol(rol);
        admin.setFacultad(facultad);

        return administradorRepository.save(admin);
    }

    public Administrador actualizarAdministrador(Long id, Administrador detalles) {
        Administrador admin = obtenerPorId(id);

        admin.setNombre(detalles.getNombre());
        admin.setTelefono(detalles.getTelefono());
        admin.setCorreoPersonal(detalles.getCorreoPersonal());
        admin.setFechaNacimiento(detalles.getFechaNacimiento());
        admin.setNumeroDocumento(detalles.getNumeroDocumento());
        admin.setEstado(detalles.isEstado());
        admin.setCodigoInstitucional(detalles.getCodigoInstitucional());
        admin.setCorreoInstitucional(detalles.getCorreoInstitucional());

        if (detalles.getFacultad() != null) {
            Facultad facultad = facultadRepository.findById(detalles.getFacultad().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Facultad no válida."));
            admin.setFacultad(facultad);
        }

        if (detalles.getRol() != null) {
            Rol rol = rolRepository.findById(detalles.getRol().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no válido."));
            admin.setRol(rol);
        }

        return administradorRepository.save(admin);
    }

    public void eliminarAdministrador(Long id) {
        Administrador admin = obtenerPorId(id);
        administradorRepository.delete(admin);
    }

    public AdministradorDTO convertirADTO(Administrador admin) {
        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(admin.getId());
        dto.setNombre(admin.getNombre());
        dto.setTelefono(admin.getTelefono());
        dto.setCorreoPersonal(admin.getCorreoPersonal());
        dto.setFechaNacimiento(admin.getFechaNacimiento());
        dto.setNumeroDocumento(admin.getNumeroDocumento());
        dto.setEstado(admin.isEstado());

        dto.setTipoDocumentoId(admin.getTipoDocumento().getId());
        dto.setTipoDocumentoNombre(admin.getTipoDocumento().getNombre());

        dto.setGeneroId(admin.getGenero().getId());
        dto.setGeneroNombre(admin.getGenero().getNombre());

        dto.setRolId(admin.getRol().getId());
        dto.setRolNombre(admin.getRol().getNombre());

        if (admin.getFacultad() != null) {
            dto.setFacultadId(admin.getFacultad().getId());
            dto.setFacultadNombre(admin.getFacultad().getNombre());
        } else {
            dto.setFacultadId(null);
            dto.setFacultadNombre("N/A");
        }

        dto.setCodigoInstitucional(admin.getCodigoInstitucional());
        dto.setCorreoInstitucional(admin.getCorreoInstitucional());

        return dto;
    }
}
