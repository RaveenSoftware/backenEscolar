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
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Get all estudiantes
    @GetMapping("/estudiantes")
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Create estudiante rest api
    @PostMapping("/estudiantes")
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Get estudiante by id rest api
    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not exist with id :" + id));
        return ResponseEntity.ok(estudiante);
    }

    // Update estudiante rest api
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not exist with id :" + id));

        estudiante.setCodigoInstitucional(estudianteDetails.getCodigoInstitucional());
        estudiante.setPrograma(estudianteDetails.getPrograma());
        estudiante.setCorreoInstitucional(estudianteDetails.getCorreoInstitucional());

        Estudiante updatedEstudiante = estudianteRepository.save(estudiante);
        return ResponseEntity.ok(updatedEstudiante);
    }

    // Delete estudiante rest api
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEstudiante(@PathVariable Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not exist with id :" + id));

        estudianteRepository.delete(estudiante);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}