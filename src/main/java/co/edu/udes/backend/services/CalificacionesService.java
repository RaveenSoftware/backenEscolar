package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.repositories.CalificacionesRepository;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalificacionesService {

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    //  Registrar calificación con cálculo automático de definitiva
    public Calificaciones registrarCalificacion(Calificaciones calificacion) {
        Estudiante estudiante = estudianteRepository.findById(calificacion.getEstudiante().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        Curso curso = cursoRepository.findById(calificacion.getCurso().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        calificacion.setEstudiante(estudiante);
        calificacion.setCurso(curso);

        double definitiva = calcularAcumulado(calificacion.getP1(), calificacion.getP2(), calificacion.getP3());
        calificacion.setDefinitiva(definitiva);

        return calificacionesRepository.save(calificacion);
    }

    //  Actualizar calificación existente
    public Calificaciones actualizarCalificacion(Long id, Calificaciones nueva) {
        Calificaciones existente = calificacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación no encontrada con ID: " + id));

        existente.setP1(nueva.getP1());
        existente.setP2(nueva.getP2());
        existente.setP3(nueva.getP3());

        double definitiva = calcularAcumulado(nueva.getP1(), nueva.getP2(), nueva.getP3());
        existente.setDefinitiva(definitiva);

        return calificacionesRepository.save(existente);
    }

    //  Obtener todas las calificaciones
    public List<Calificaciones> obtenerTodas() {
        return calificacionesRepository.findAll();
    }

    //  Obtener calificación por ID
    public Calificaciones obtenerPorId(Long id) {
        return calificacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación no encontrada con ID: " + id));
    }

    //  Eliminar calificación
    public void eliminar(Long id) {
        Calificaciones existente = obtenerPorId(id);
        calificacionesRepository.delete(existente);
    }

    //  Cálculo acumulado final
    private double calcularAcumulado(Double p1, Double p2, Double p3) {
        if (p1 == null) p1 = 0.0;
        if (p2 == null) p2 = 0.0;
        if (p3 == null) p3 = 0.0;
        return Math.round((p1 * 0.3 + p2 * 0.3 + p3 * 0.4) * 100.0) / 100.0;
    }

    //  Calificaciones por estudiante
    public List<Calificaciones> obtenerPorEstudiante(Long estudianteId) {
        return calificacionesRepository.findByEstudianteId(estudianteId);
    }

    //  Promedio general del estudiante
    public double calcularPromedioGeneral(Long estudianteId) {
        List<Calificaciones> calificaciones = calificacionesRepository.findByEstudianteId(estudianteId);

        if (calificaciones.isEmpty()) return 0.0;

        double suma = 0.0;
        for (Calificaciones c : calificaciones) {
            suma += c.getDefinitiva() != null ? c.getDefinitiva() : 0.0;
        }

        return Math.round((suma / calificaciones.size()) * 100.0) / 100.0;
    }

    //  METI NUEVO: Proyección de nota final
    public double proyectarNotaFinal(Long estudianteId, Long cursoId, double meta) {
        Calificaciones calificacion = calificacionesRepository.findByEstudianteIdAndCursoId(estudianteId, cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró calificación para el estudiante y curso"));

        double p1 = calificacion.getP1() != null ? calificacion.getP1() : 0.0;
        double p2 = calificacion.getP2() != null ? calificacion.getP2() : 0.0;
        Double p3 = calificacion.getP3();

        if (p3 != null) {
            return Math.round(calificacion.getDefinitiva() * 100.0) / 100.0;
        }

        double parcial = p1 * 0.3 + p2 * 0.3;
        double faltante = (meta - parcial) / 0.4;
        return Math.round(faltante * 100.0) / 100.0;
    }

    //  METI NUEVO: Reporte de desempeño por curso
    public Map<String, Object> generarReporteCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        List<Calificaciones> calificaciones = calificacionesRepository.findAll().stream()
                .filter(c -> c.getCurso().getId().equals(cursoId))
                .toList();

        Map<String, Object> reporte = new HashMap<>();
        reporte.put("curso", curso.getNombre());
        reporte.put("totalEstudiantes", calificaciones.size());

        if (calificaciones.isEmpty()) {
            reporte.put("promedio", 0.0);
            reporte.put("maxima", 0.0);
            reporte.put("minima", 0.0);
            reporte.put("reprobados", 0);
            return reporte;
        }

        double suma = 0.0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        int reprobados = 0;

        for (Calificaciones c : calificaciones) {
            double def = c.getDefinitiva() != null ? c.getDefinitiva() : 0.0;
            suma += def;
            if (def > max) max = def;
            if (def < min) min = def;
            if (def < 3.0) reprobados++;
        }

        double promedio = Math.round((suma / calificaciones.size()) * 100.0) / 100.0;

        reporte.put("promedio", promedio);
        reporte.put("maxima", max);
        reporte.put("minima", min);
        reporte.put("reprobados", reprobados);

        return reporte;
    }

    //  METI NUEVO: Reporte tipo boletín por estudiante
    public Map<String, Object> generarBoletinEstudiante(Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + estudianteId));

        List<Calificaciones> calificaciones = calificacionesRepository.findByEstudianteId(estudianteId);

        double promedio = calcularPromedioGeneral(estudianteId);

        Map<String, Object> boletin = new HashMap<>();
        boletin.put("estudiante", estudiante.getNombre());
        boletin.put("documento", estudiante.getNumeroDocumento());
        boletin.put("promedioGeneral", promedio);
        boletin.put("calificaciones", calificaciones);

        return boletin;
    }

    // METI → Agregar comentario del docente
    public Calificaciones agregarComentarioDocente(Long calificacionId, String comentario) {
        Calificaciones calificacion = obtenerPorId(calificacionId);
        calificacion.setComentariosDocente(comentario);
        return calificacionesRepository.save(calificacion);
    }

    // METI → Consultar comentario del docente
    public String obtenerComentarioDocente(Long calificacionId) {
        Calificaciones calificacion = obtenerPorId(calificacionId);
        return calificacion.getComentariosDocente();
    }
}
