package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.SemestreAcademico;
import co.edu.udes.backend.repositories.SemestreAcademicoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/semestres-academicos")
@CrossOrigin(origins = "*")
public class SemestreAcademicoController {

    private final SemestreAcademicoRepository semestreAcademicoRepository;

    @Autowired
    public SemestreAcademicoController(SemestreAcademicoRepository semestreAcademicoRepository) {
        this.semestreAcademicoRepository = semestreAcademicoRepository;
    }

    // Obtener todos los semestres académicos
    @GetMapping
    public ResponseEntity<List<SemestreAcademico>> getAllSemestresAcademicos() {
        List<SemestreAcademico> lista = semestreAcademicoRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    // Crear un nuevo semestre académico
    @PostMapping
    public ResponseEntity<SemestreAcademico> createSemestreAcademico(@RequestBody SemestreAcademico semestreAcademico) {
        SemestreAcademico saved = semestreAcademicoRepository.save(semestreAcademico);
        return ResponseEntity.ok(saved);
    }

    // Obtener semestre académico por ID
    @GetMapping("/{id}")
    public ResponseEntity<SemestreAcademico> getSemestreAcademicoById(@PathVariable Long id) {
        SemestreAcademico semestre = semestreAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semestre académico no existe con ID: " + id));
        return ResponseEntity.ok(semestre);
    }

    // Actualizar semestre académico
    @PutMapping("/{id}")
    public ResponseEntity<SemestreAcademico> updateSemestreAcademico(@PathVariable Long id, @RequestBody SemestreAcademico semestreDetails) {
        SemestreAcademico semestre = semestreAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semestre académico no existe con ID: " + id));

        semestre.setYear(semestreDetails.getYear());
        semestre.setPeriodoAcademico(semestreDetails.getPeriodoAcademico());
        semestre.setDescripcion(semestreDetails.getDescripcion());

        SemestreAcademico updated = semestreAcademicoRepository.save(semestre);
        return ResponseEntity.ok(updated);
    }

    // Eliminar semestre académico
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSemestreAcademico(@PathVariable Long id) {
        SemestreAcademico semestre = semestreAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semestre académico no existe con ID: " + id));

        semestreAcademicoRepository.delete(semestre);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
