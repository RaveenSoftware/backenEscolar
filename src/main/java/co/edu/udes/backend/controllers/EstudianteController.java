package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.services.EstudianteService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<?> createEstudiante(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.registrarEstudiante(estudiante));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        try {
            return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, estudianteDetails));
        } catch (ResourceNotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
