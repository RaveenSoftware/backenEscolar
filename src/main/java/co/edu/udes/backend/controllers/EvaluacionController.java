package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Evaluacion;
import co.edu.udes.backend.services.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    // Crear evaluación
    @PostMapping
    public ResponseEntity<Evaluacion> crearEvaluacion(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionService.crearEvaluacion(evaluacion);
        return ResponseEntity.ok(nueva);
    }

    // Obtener todas las evaluaciones
    @GetMapping
    public ResponseEntity<List<Evaluacion>> getAll() {
        return ResponseEntity.ok(evaluacionService.listarEvaluaciones());
    }

    // Obtener una evaluación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.obtenerPorId(id));
    }

    // Actualizar evaluación
    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        Evaluacion actualizada = evaluacionService.actualizarEvaluacion(id, evaluacion);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar evaluación
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        evaluacionService.eliminarEvaluacion(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
