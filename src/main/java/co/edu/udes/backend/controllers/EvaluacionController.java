package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Evaluacion;
import co.edu.udes.backend.repositories.EvaluacionRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    private final EvaluacionRepository evaluacionRepository;

    @Autowired
    public EvaluacionController(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    // Obtener todas las evaluaciones
    @GetMapping
    public ResponseEntity<List<Evaluacion>> getAll() {
        List<Evaluacion> evaluaciones = evaluacionRepository.findAll();
        return ResponseEntity.ok(evaluaciones);
    }

    // Crear una nueva evaluación
    @PostMapping
    public ResponseEntity<Evaluacion> create(@RequestBody Evaluacion evaluacion) {
        Evaluacion savedEvaluacion = evaluacionRepository.save(evaluacion);
        return ResponseEntity.ok(savedEvaluacion);
    }

    // Obtener evaluación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getById(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluación no existe con ID: " + id));
        return ResponseEntity.ok(evaluacion);
    }

    // Eliminar evaluación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluación no existe con ID: " + id));
        evaluacionRepository.delete(evaluacion);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
