package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Poligrafo;
import co.edu.udes.backend.services.HistorialAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/historial")
public class HistorialAcademicoController {

    @Autowired
    private HistorialAcademicoService historialService;

    // Obtener todas las calificaciones de un estudiante
    @GetMapping("/calificaciones/{idEstudiante}")
    public ResponseEntity<List<Calificaciones>> getCalificaciones(@PathVariable Long idEstudiante) {
        List<Calificaciones> calificaciones = historialService.obtenerCalificacionesPorEstudiante(idEstudiante);
        return ResponseEntity.ok(calificaciones);
    }

    // Obtener todos los registros del poligrafo del estudiante
    @GetMapping("/poligrafo/{idEstudiante}")
    public ResponseEntity<List<Poligrafo>> getPoligrafo(@PathVariable Long idEstudiante) {
        List<Poligrafo> poligrafos = historialService.obtenerResumenPoligrafo(idEstudiante);
        return ResponseEntity.ok(poligrafos);
    }

    // Obtener promedio general
    @GetMapping("/promedio/{idEstudiante}")
    public ResponseEntity<Map<String, Double>> getPromedio(@PathVariable Long idEstudiante) {
        double promedio = historialService.calcularPromedioGeneral(idEstudiante);
        Map<String, Double> response = new HashMap<>();
        response.put("promedio", promedio);
        return ResponseEntity.ok(response);
    }

    // Obtener número de asignaturas aprobadas
    @GetMapping("/aprobadas/{idEstudiante}")
    public ResponseEntity<Map<String, Long>> getAprobadas(@PathVariable Long idEstudiante) {
        long aprobadas = historialService.contarAprobadas(idEstudiante);
        Map<String, Long> response = new HashMap<>();
        response.put("aprobadas", aprobadas);
        return ResponseEntity.ok(response);
    }

    // Obtener número de asignaturas reprobadas
    @GetMapping("/reprobadas/{idEstudiante}")
    public ResponseEntity<Map<String, Long>> getReprobadas(@PathVariable Long idEstudiante) {
        long reprobadas = historialService.contarReprobadas(idEstudiante);
        Map<String, Long> response = new HashMap<>();
        response.put("reprobadas", reprobadas);
        return ResponseEntity.ok(response);
    }
}
