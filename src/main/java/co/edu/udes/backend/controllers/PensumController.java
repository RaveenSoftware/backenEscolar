package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Pensum;
import co.edu.udes.backend.repositories.PensumRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pensums")
@CrossOrigin(origins = "*")
public class PensumController {

    private final PensumRepository pensumRepository;

    @Autowired
    public PensumController(PensumRepository pensumRepository) {
        this.pensumRepository = pensumRepository;
    }

    // Obtener todos los pensums
    @GetMapping
    public ResponseEntity<List<Pensum>> getAllPensums() {
        List<Pensum> pensums = pensumRepository.findAll();
        return ResponseEntity.ok(pensums);
    }

    // Crear un nuevo pensum
    @PostMapping
    public ResponseEntity<Pensum> createPensum(@RequestBody Pensum pensum) {
        Pensum saved = pensumRepository.save(pensum);
        return ResponseEntity.ok(saved);
    }

    // Obtener pensum por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pensum> getPensumById(@PathVariable Long id) {
        Pensum pensum = pensumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pensum no existe con ID: " + id));
        return ResponseEntity.ok(pensum);
    }

    // Actualizar un pensum existente
    @PutMapping("/{id}")
    public ResponseEntity<Pensum> updatePensum(@PathVariable Long id, @RequestBody Pensum pensumDetails) {
        Pensum pensum = pensumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pensum no existe con ID: " + id));

        pensum.setCodigoPensum(pensumDetails.getCodigoPensum());
        pensum.setAsignaturas(pensumDetails.getAsignaturas());
        pensum.setProgramaAcademico(pensumDetails.getProgramaAcademico());
        pensum.setEstado(pensumDetails.isEstado());

        Pensum updated = pensumRepository.save(pensum);
        return ResponseEntity.ok(updated);
    }

    // Eliminar pensum
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePensum(@PathVariable Long id) {
        Pensum pensum = pensumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pensum no existe con ID: " + id));

        pensumRepository.delete(pensum);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
