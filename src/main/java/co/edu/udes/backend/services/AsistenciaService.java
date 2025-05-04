package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Asistencia;
import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.models.EstadoAsistencia;
import co.edu.udes.backend.repositories.AsistenciaRepository;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Registrar una nueva asistencia
    public Asistencia registrarAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    // Obtener asistencia por ID
    public Asistencia obtenerPorId(Long id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con ID: " + id));
    }

    // Actualizar asistencia
    public Asistencia actualizarAsistencia(Long id, Asistencia asistenciaDetails) {
        Asistencia asistencia = obtenerPorId(id);
        asistencia.setCurso(asistenciaDetails.getCurso());
        asistencia.setEstudiante(asistenciaDetails.getEstudiante());
        asistencia.setFecha(asistenciaDetails.getFecha());
        asistencia.setEstado(asistenciaDetails.getEstado());
        return asistenciaRepository.save(asistencia);
    }

    // Obtener todas las asistencias
    public List<Asistencia> obtenerTodas() {
        return asistenciaRepository.findAll();
    }

    // Obtener asistencias de un estudiante
    public List<Asistencia> obtenerPorEstudiante(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId);
    }

    // Obtener asistencias por curso
    public List<Asistencia> obtenerPorCurso(Long cursoId) {
        return asistenciaRepository.findByCursoId(cursoId);
    }

    // Obtener asistencias por estudiante y fecha
    public List<Asistencia> obtenerPorEstudianteYFecha(Long estudianteId, LocalDate fecha) {
        return asistenciaRepository.findByEstudianteIdAndFecha(estudianteId, fecha);
    }

    // Obtener ausencias
    public List<Asistencia> obtenerAusencias(Long estudianteId) {
        return asistenciaRepository.findByEstudianteIdAndEstado(estudianteId, EstadoAsistencia.AUSENTE);
    }

    // Eliminar una asistencia
    public void eliminarAsistencia(Long id) {
        Asistencia asistencia = obtenerPorId(id);
        asistenciaRepository.delete(asistencia);
    }
}
