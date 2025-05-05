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

    //  CRUD BÁSICO

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

    //  CALIFICACIONES POR ESTUDIANTE

    @GetMapping("/por-estudiante/{estudianteId}")
    public ResponseEntity<List<Calificaciones>> getCalificacionesPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(calificacionesService.obtenerPorEstudiante(estudianteId));
    }

    //  PROMEDIO GENERAL

    @GetMapping("/promedio/{estudianteId}")
    public ResponseEntity<Map<String, Double>> getPromedioGeneral(@PathVariable Long estudianteId) {
        double promedio = calificacionesService.calcularPromedioGeneral(estudianteId);
        Map<String, Double> response = new HashMap<>();
        response.put("promedioGeneral", promedio);
        return ResponseEntity.ok(response);
    }

    //  PROYECCIÓN DE NOTA

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

    //  REPORTE POR CURSO

    @GetMapping("/reporte/curso/{cursoId}")
    public ResponseEntity<Map<String, Object>> generarReportePorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(calificacionesService.generarReporteCurso(cursoId));
    }

    //  BOLETÍN ESTUDIANTE

    @GetMapping("/reporte/boletin/{estudianteId}")
    public ResponseEntity<Map<String, Object>> generarBoletinEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(calificacionesService.generarBoletinEstudiante(estudianteId));
    }

    // 🗨 NUEVO → AGREGAR COMENTARIO

    @PostMapping("/{id}/comentario")
    public ResponseEntity<Calificaciones> agregarComentario(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String comentario = payload.get("comentario");
        Calificaciones actualizado = calificacionesService.agregarComentarioDocente(id, comentario);
        return ResponseEntity.ok(actualizado);
    }

    // 🗨 NUEVO → OBTENER COMENTARIO

    @GetMapping("/{id}/comentario")
    public ResponseEntity<Map<String, String>> obtenerComentario(@PathVariable Long id) {
        String comentario = calificacionesService.obtenerComentarioDocente(id);
        Map<String, String> response = new HashMap<>();
        response.put("comentario", comentario != null ? comentario : "Sin comentario");
        return ResponseEntity.ok(response);
    }
}
