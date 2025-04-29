package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Asistencia;
import co.edu.udes.backend.repositories.AsistenciaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    // Listar todas las asistencias
    public List<Asistencia> listarTodas() {
        return asistenciaRepository.findAll();
    }

    // Buscar asistencia por ID
    public Asistencia buscarPorId(Long id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no existe con id: " + id));
    }

    // Registrar una nueva asistencia
    public Asistencia registrar(Asistencia asistencia) {
        boolean yaExiste = asistenciaRepository.existsByEstudianteIdAndCursoIdAndFecha(
                asistencia.getEstudiante().getId(),
                asistencia.getCurso().getId(),
                asistencia.getFecha()
        );

        if (yaExiste) {
            throw new IllegalStateException("Ya existe una asistencia registrada para este estudiante, curso y fecha.");
        }

        return asistenciaRepository.save(asistencia);
    }

    // Actualizar asistencia
    public Asistencia actualizar(Long id, Asistencia nueva) {
        Asistencia asistencia = buscarPorId(id);
        asistencia.setCurso(nueva.getCurso());
        asistencia.setEstudiante(nueva.getEstudiante());
        asistencia.setFecha(nueva.getFecha());
        asistencia.setEstado(nueva.getEstado());
        return asistenciaRepository.save(asistencia);
    }

    // Eliminar asistencia
    public void eliminar(Long id) {
        Asistencia asistencia = buscarPorId(id);
        asistenciaRepository.delete(asistencia);
    }
}

