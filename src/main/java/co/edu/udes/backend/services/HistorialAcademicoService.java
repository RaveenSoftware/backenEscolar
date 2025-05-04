package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Poligrafo;
import co.edu.udes.backend.repositories.CalificacionesRepository;
import co.edu.udes.backend.repositories.PoligrafoRepository;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialAcademicoService {

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Autowired
    private PoligrafoRepository poligrafoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Obtener todas las calificaciones de un estudiante
    public List<Calificaciones> obtenerCalificacionesPorEstudiante(Long estudianteId) {
        validarEstudianteExistente(estudianteId);
        return calificacionesRepository.findByEstudianteId(estudianteId);
    }

    // Obtener registros del polígrafos (desempeño por asignatura)
    public List<Poligrafo> obtenerResumenPoligrafo(Long estudianteId) {
        validarEstudianteExistente(estudianteId);
        return poligrafoRepository.findByEstudianteId(estudianteId);
    }

    // Calcular promedio general del estudiante
    public double calcularPromedioGeneral(Long estudianteId) {
        validarEstudianteExistente(estudianteId);
        List<Poligrafo> registros = poligrafoRepository.findByEstudianteId(estudianteId);
        if (registros.isEmpty()) return 0.0;

        double suma = 0.0;
        for (Poligrafo p : registros) {
            suma += p.getPromedio();
        }
        return suma / registros.size();
    }

    // Contar asignaturas aprobadas
    public long contarAprobadas(Long estudianteId) {
        validarEstudianteExistente(estudianteId);
        List<Poligrafo> registros = poligrafoRepository.findByEstudianteId(estudianteId);
        return registros.stream().filter(p -> p.getCalificaciones().getDefinitiva() >= 3.0).count();
    }

    // Contar asignaturas reprobadas
    public long contarReprobadas(Long estudianteId) {
        validarEstudianteExistente(estudianteId);
        List<Poligrafo> registros = poligrafoRepository.findByEstudianteId(estudianteId);
        return registros.stream().filter(p -> p.getCalificaciones().getDefinitiva() < 3.0).count();
    }

    private void validarEstudianteExistente(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estudiante no existe con ID: " + id);
        }
    }
}
