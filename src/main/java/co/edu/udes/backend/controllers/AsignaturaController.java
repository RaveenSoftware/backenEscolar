package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.repositories.AsignaturaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/asignaturas")
@CrossOrigin(origins = "*")
public class AsignaturaController {

    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaController(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    // Obtener todas las asignaturas
    @GetMapping
    public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
        List<Asignatura> asignaturas = asignaturaRepository.findAll();
        return ResponseEntity.ok(asignaturas);
    }

    // Crear una nueva asignatura
    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura savedAsignatura = asignaturaRepository.save(asignatura);
        return ResponseEntity.ok(savedAsignatura);
    }

    // Obtener asignatura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no existe con ID: " + id));
        return ResponseEntity.ok(asignatura);
    }

    // Actualizar asignatura existente
    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Long id, @RequestBody Asignatura asignaturaDetails) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no existe con ID: " + id));

        asignatura.setCodigo(asignaturaDetails.getCodigo());
        asignatura.setNombre(asignaturaDetails.getNombre());
        asignatura.setPredecesora(asignaturaDetails.getPredecesora());
        asignatura.setNumeroSemestre(asignaturaDetails.getNumeroSemestre());
        asignatura.setNumeroCreditos(asignaturaDetails.getNumeroCreditos());

        Asignatura updatedAsignatura = asignaturaRepository.save(asignatura);
        return ResponseEntity.ok(updatedAsignatura);
    }

    // Eliminar asignatura
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsignatura(@PathVariable Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no existe con ID: " + id));

        asignaturaRepository.delete(asignatura);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
