package co.edu.udes.backend.services;

import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    public Docente crearDocente(Docente docente) {
        Rol rol = rolRepository.findById(docente.getRol().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        Facultad facultad = facultadRepository.findById(docente.getFacultad().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Facultad no encontrada"));

        docente.setRol(rol);
        docente.setFacultad(facultad);

        return docenteRepository.save(docente);
    }

    public List<Docente> obtenerTodos() {
        return docenteRepository.findAll();
    }

    public Docente obtenerPorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + id));
    }

    public Docente actualizarDocente(Long id, Docente detalles) {
        Docente docente = obtenerPorId(id);

        docente.setNombre(detalles.getNombre());
        docente.setCorreoPersonal(detalles.getCorreoPersonal());
        docente.setTelefono(detalles.getTelefono());
        docente.setFechaNacimiento(detalles.getFechaNacimiento());
        docente.setNumeroDocumento(detalles.getNumeroDocumento());
        docente.setEstado(detalles.isEstado());
        docente.setEspecialidad(detalles.getEspecialidad());
        docente.setCodigoInstitucional(detalles.getCodigoInstitucional());
        docente.setCorreoInstitucional(detalles.getCorreoInstitucional());

        // Validar facultad
        if (detalles.getFacultad() != null) {
            Facultad facultad = facultadRepository.findById(detalles.getFacultad().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Facultad no válida."));
            docente.setFacultad(facultad);
        }

        // Validar rol
        if (detalles.getRol() != null) {
            Rol rol = rolRepository.findById(detalles.getRol().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no válido."));
            docente.setRol(rol);
        }

        return docenteRepository.save(docente);
    }

    public void eliminarDocente(Long id) {
        Docente docente = obtenerPorId(id);
        docenteRepository.delete(docente);
    }
}
