package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Aula;
import co.edu.udes.backend.repositories.AulaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/aulas")
@CrossOrigin(origins = "*")
public class AulaController {

    private final AulaRepository aulaRepository;

    @Autowired
    public AulaController(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    // Obtener todas las aulas
    @GetMapping
    public ResponseEntity<List<Aula>> getAllAulas() {
        List<Aula> aulas = aulaRepository.findAll();
        return ResponseEntity.ok(aulas);
    }

    // Crear nueva aula
    @PostMapping
    public ResponseEntity<Aula> createAula(@RequestBody Aula aula) {
        Aula savedAula = aulaRepository.save(aula);
        return ResponseEntity.ok(savedAula);
    }

    // Obtener aula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aula> getAulaById(@PathVariable Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula no existe con ID: " + id));
        return ResponseEntity.ok(aula);
    }

    // Actualizar aula existente
    @PutMapping("/{id}")
    public ResponseEntity<Aula> updateAula(@PathVariable Long id, @RequestBody Aula aulaDetails) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula no existe con ID: " + id));

        aula.setNombre(aulaDetails.getNombre());
        aula.setBloque(aulaDetails.getBloque());
        aula.setCodigo(aulaDetails.getCodigo());
        aula.setEstado(aulaDetails.isEstado());

        Aula updatedAula = aulaRepository.save(aula);
        return ResponseEntity.ok(updatedAula);
    }

    // Eliminar aula
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAula(@PathVariable Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula no existe con ID: " + id));

        aulaRepository.delete(aula);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
