package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.services.CalificacionesService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionesController {

    @Autowired
    private CalificacionesService calificacionesService;

    // Obtener todas las calificaciones
    @GetMapping
    public ResponseEntity<List<Calificaciones>> getAllCalificaciones() {
        List<Calificaciones> lista = calificacionesService.obtenerTodas();
        return ResponseEntity.ok(lista);
    }

    // Crear una nueva calificación
    @PostMapping
    public ResponseEntity<Calificaciones> createCalificacion(@RequestBody Calificaciones calificacion) {
        Calificaciones nueva = calificacionesService.registrarCalificacion(calificacion);
        return ResponseEntity.ok(nueva);
    }

    // Obtener calificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Calificaciones> getCalificacionById(@PathVariable Long id) {
        Calificaciones calificacion = calificacionesService.obtenerPorId(id);
        return ResponseEntity.ok(calificacion);
    }

    // Actualizar calificación existente
    @PutMapping("/{id}")
    public ResponseEntity<Calificaciones> updateCalificacion(@PathVariable Long id, @RequestBody Calificaciones calificacionDetails) {
        Calificaciones actualizada = calificacionesService.actualizarCalificacion(id, calificacionDetails);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar calificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCalificacion(@PathVariable Long id) {
        calificacionesService.eliminar(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
