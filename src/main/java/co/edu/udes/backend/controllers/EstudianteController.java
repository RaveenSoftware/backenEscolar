package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // Obtener todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return ResponseEntity.ok(estudiantes);
    }

    // Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<Estudiante> createEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante savedEstudiante = estudianteRepository.save(estudiante);
        return ResponseEntity.ok(savedEstudiante);
    }

    // Obtener estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no existe con ID: " + id));
        return ResponseEntity.ok(estudiante);
    }

    // Actualizar un estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no existe con ID: " + id));

        estudiante.setCodigoInstitucional(estudianteDetails.getCodigoInstitucional());
        estudiante.setPrograma(estudianteDetails.getPrograma());
        estudiante.setCorreoInstitucional(estudianteDetails.getCorreoInstitucional());

        Estudiante updatedEstudiante = estudianteRepository.save(estudiante);
        return ResponseEntity.ok(updatedEstudiante);
    }

    // Eliminar estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEstudiante(@PathVariable Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no existe con ID: " + id));

        estudianteRepository.delete(estudiante);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
