package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.services.CalificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionesController {

    @Autowired
    private CalificacionesService calificacionesService;

    // ===== CRUD BÁSICO =====

    @GetMapping
    public ResponseEntity<List<Calificaciones>> getAllCalificaciones() {
        return ResponseEntity.ok(calificacionesService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Calificaciones> createCalificacion(@RequestBody Calificaciones calificacion) {
        return ResponseEntity.ok(calificacionesService.registrarCalificacion(calificacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificaciones> getCalificacionById(@PathVariable Long id) {
        return ResponseEntity.ok(calificacionesService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calificaciones> updateCalificacion(@PathVariable Long id, @RequestBody Calificaciones nueva) {
        return ResponseEntity.ok(calificacionesService.actualizarCalificacion(id, nueva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCalificacion(@PathVariable Long id) {
        calificacionesService.eliminar(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // ===== ENDPOINTS ADICIONALES =====

    // Calificaciones por estudiante
    @GetMapping("/por-estudiante/{estudianteId}")
    public ResponseEntity<List<Calificaciones>> getCalificacionesPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(calificacionesService.obtenerPorEstudiante(estudianteId));
    }

    // Promedio general de un estudiante
    @GetMapping("/promedio/{estudianteId}")
    public ResponseEntity<Map<String, Double>> getPromedioGeneral(@PathVariable Long estudianteId) {
        double promedio = calificacionesService.calcularPromedioGeneral(estudianteId);
        Map<String, Double> response = new HashMap<>();
        response.put("promedioGeneral", promedio);
        return ResponseEntity.ok(response);
    }

    // Proyección de nota final
    @GetMapping("/proyeccion/{estudianteId}/{cursoId}")
    public ResponseEntity<Map<String, Double>> proyectarNotaFinal(
            @PathVariable Long estudianteId,
            @PathVariable Long cursoId,
            @RequestParam(name = "meta", defaultValue = "3.0") double meta) {

        double notaRequerida = calificacionesService.proyectarNotaFinal(estudianteId, cursoId, meta);
        Map<String, Double> response = new HashMap<>();
        response.put("notaRequeridaEnP3", notaRequerida);
        return ResponseEntity.ok(response);
    }

    // ===== NUEVO: Reporte tipo boletín por estudiante =====
    @GetMapping("/boletin/{estudianteId}")
    public ResponseEntity<Map<String, Object>> getBoletinEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(calificacionesService.generarBoletinEstudiante(estudianteId));
    }

    // ===== NUEVO: Estadísticas del curso (promedio, máximo, mínimo, reprobados) =====
    @GetMapping("/reporte/curso/{cursoId}")
    public ResponseEntity<Map<String, Object>> getReporteCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(calificacionesService.generarReporteCurso(cursoId));
    }
}
