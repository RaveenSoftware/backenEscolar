package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Poligrafo;
import co.edu.udes.backend.repositories.CalificacionesRepository;
import co.edu.udes.backend.repositories.PoligrafoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialAcademicoService {

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Autowired
    private PoligrafoRepository poligrafoRepository;

    // Obtener todas las calificaciones de un estudiante
    public List<Calificaciones> obtenerCalificacionesPorEstudiante(Long estudianteId) {
        return calificacionesRepository.findByEstudianteId(estudianteId);
    }

    // Obtener historial de polígrafo por estudiante (si aplica)
    public List<Poligrafo> obtenerResumenPoligrafo(Long estudianteId) {
        return poligrafoRepository.findByEstudianteId(estudianteId);
    }

    // Calcular promedio definitivo del estudiante
    public double calcularPromedioGeneral(Long estudianteId) {
        List<Calificaciones> calificaciones = calificacionesRepository.findByEstudianteId(estudianteId);

        if (calificaciones.isEmpty()) return 0.0;

        double suma = calificaciones.stream().mapToDouble(Calificaciones::getDefinitiva).sum();
        return suma / calificaciones.size();
    }

    // Contar asignaturas aprobadas
    public long contarAprobadas(Long estudianteId) {
        return calificacionesRepository.findByEstudianteId(estudianteId).stream()
                .filter(c -> c.getDefinitiva() >= 3.0)
                .count();
    }

    // Contar asignaturas reprobadas
    public long contarReprobadas(Long estudianteId) {
        return calificacionesRepository.findByEstudianteId(estudianteId).stream()
                .filter(c -> c.getDefinitiva() < 3.0)
                .count();
    }
}
