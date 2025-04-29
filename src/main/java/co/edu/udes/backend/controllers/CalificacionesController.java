package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.repositories.CalificacionesRepository;
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

    private final CalificacionesRepository calificacionesRepository;

    @Autowired
    public CalificacionesController(CalificacionesRepository calificacionesRepository) {
        this.calificacionesRepository = calificacionesRepository;
    }

    // Obtener todas las calificaciones
    @GetMapping
    public ResponseEntity<List<Calificaciones>> getAllCalificaciones() {
        List<Calificaciones> calificaciones = calificacionesRepository.findAll();
        return ResponseEntity.ok(calificaciones);
    }

    // Crear una nueva calificación
    @PostMapping
    public ResponseEntity<Calificaciones> createCalificacion(@RequestBody Calificaciones calificacion) {
        Calificaciones savedCalificacion = calificacionesRepository.save(calificacion);
        return ResponseEntity.ok(savedCalificacion);
    }

    // Obtener calificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Calificaciones> getCalificacionById(@PathVariable Long id) {
        Calificaciones calificacion = calificacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación no existe con ID: " + id));
        return ResponseEntity.ok(calificacion);
    }

    // Actualizar calificación existente
    @PutMapping("/{id}")
    public ResponseEntity<Calificaciones> updateCalificacion(@PathVariable Long id, @RequestBody Calificaciones calificacionDetails) {
        Calificaciones calificacion = calificacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación no existe con ID: " + id));

        calificacion.setP1(calificacionDetails.getP1());
        calificacion.setP2(calificacionDetails.getP2());
        calificacion.setCurso(calificacionDetails.getCurso());
        calificacion.setDefinitiva(calificacionDetails.getDefinitiva());

        Calificaciones updatedCalificacion = calificacionesRepository.save(calificacion);
        return ResponseEntity.ok(updatedCalificacion);
    }

    // Eliminar calificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCalificacion(@PathVariable Long id) {
        Calificaciones calificacion = calificacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación no existe con ID: " + id));

        calificacionesRepository.delete(calificacion);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
