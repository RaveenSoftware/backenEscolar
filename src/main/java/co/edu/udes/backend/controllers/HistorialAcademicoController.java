package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Poligrafo;
import co.edu.udes.backend.services.HistorialAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/historial")
@CrossOrigin(origins = "*")
public class HistorialAcademicoController {

    @Autowired
    private HistorialAcademicoService historialService;

    // GET - Calificaciones de un estudiante
    @GetMapping("/calificaciones/{estudianteId}")
    public ResponseEntity<List<Calificaciones>> obtenerCalificaciones(@PathVariable Long estudianteId) {
        List<Calificaciones> calificaciones = historialService.obtenerCalificacionesPorEstudiante(estudianteId);
        return ResponseEntity.ok(calificaciones);
    }

    // GET - Polígrafo (resumen de desempeño)
    @GetMapping("/poligrafo/{estudianteId}")
    public ResponseEntity<List<Poligrafo>> obtenerResumenPoligrafo(@PathVariable Long estudianteId) {
        List<Poligrafo> poligrafos = historialService.obtenerResumenPoligrafo(estudianteId);
        return ResponseEntity.ok(poligrafos);
    }

    // GET - Promedio general del estudiante
    @GetMapping("/promedio/{estudianteId}")
    public ResponseEntity<Map<String, Double>> obtenerPromedio(@PathVariable Long estudianteId) {
        double promedio = historialService.calcularPromedioGeneral(estudianteId);
        Map<String, Double> response = new HashMap<>();
        response.put("promedio", promedio);
        return ResponseEntity.ok(response);
    }

    // GET - Número de asignaturas aprobadas
    @GetMapping("/aprobadas/{estudianteId}")
    public ResponseEntity<Map<String, Long>> obtenerAprobadas(@PathVariable Long estudianteId) {
        long cantidad = historialService.contarAprobadas(estudianteId);
        Map<String, Long> response = new HashMap<>();
        response.put("aprobadas", cantidad);
        return ResponseEntity.ok(response);
    }

    // GET - Número de asignaturas reprobadas
    @GetMapping("/reprobadas/{estudianteId}")
    public ResponseEntity<Map<String, Long>> obtenerReprobadas(@PathVariable Long estudianteId) {
        long cantidad = historialService.contarReprobadas(estudianteId);
        Map<String, Long> response = new HashMap<>();
        response.put("reprobadas", cantidad);
        return ResponseEntity.ok(response);
    }
}
