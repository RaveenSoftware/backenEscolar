// EstudianteService.java
package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.models.ProgramaAcademico;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.repositories.ProgramaAcademicoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ProgramaAcademicoRepository programaAcademicoRepository;

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
        estudiante.setPrograma(programa);

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

        return estudianteRepository.save(estudiante);
    }

    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = obtenerPorId(id);
        estudianteRepository.delete(estudiante);
    }
}
