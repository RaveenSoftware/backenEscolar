package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.AulaHorario;
import co.edu.udes.backend.repositories.AulaHorarioRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/aula-horarios")
@CrossOrigin(origins = "*")
public class AulaHorarioController {

    private final AulaHorarioRepository aulaHorarioRepository;

    @Autowired
    public AulaHorarioController(AulaHorarioRepository aulaHorarioRepository) {
        this.aulaHorarioRepository = aulaHorarioRepository;
    }

    // Obtener todos los registros de AulaHorario
    @GetMapping
    public ResponseEntity<List<AulaHorario>> getAllAulaHorarios() {
        List<AulaHorario> aulaHorarios = aulaHorarioRepository.findAll();
        return ResponseEntity.ok(aulaHorarios);
    }

    // Crear un nuevo registro de AulaHorario
    @PostMapping
    public ResponseEntity<AulaHorario> createAulaHorario(@RequestBody AulaHorario aulaHorario) {
        AulaHorario savedAulaHorario = aulaHorarioRepository.save(aulaHorario);
        return ResponseEntity.ok(savedAulaHorario);
    }

    // Obtener AulaHorario por ID
    @GetMapping("/{id}")
    public ResponseEntity<AulaHorario> getAulaHorarioById(@PathVariable Long id) {
        AulaHorario aulaHorario = aulaHorarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AulaHorario no existe con ID: " + id));
        return ResponseEntity.ok(aulaHorario);
    }

    // Actualizar un AulaHorario existente
    @PutMapping("/{id}")
    public ResponseEntity<AulaHorario> updateAulaHorario(@PathVariable Long id, @RequestBody AulaHorario aulaHorarioDetails) {
        AulaHorario aulaHorario = aulaHorarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AulaHorario no existe con ID: " + id));

        aulaHorario.setAula(aulaHorarioDetails.getAula());
        aulaHorario.setHorario(aulaHorarioDetails.getHorario());
        aulaHorario.setEstado(aulaHorarioDetails.isEstado());
        aulaHorario.setCurso(aulaHorarioDetails.getCurso());

        AulaHorario updatedAulaHorario = aulaHorarioRepository.save(aulaHorario);
        return ResponseEntity.ok(updatedAulaHorario);
    }

    // Eliminar un AulaHorario
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAulaHorario(@PathVariable Long id) {
        AulaHorario aulaHorario = aulaHorarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AulaHorario no existe con ID: " + id));

        aulaHorarioRepository.delete(aulaHorario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
