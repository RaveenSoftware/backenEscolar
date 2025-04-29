package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.ProgramaAcademico;
import co.edu.udes.backend.repositories.ProgramaAcademicoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/programas-academicos")
@CrossOrigin(origins = "*")
public class ProgramaAcademicoController {

    private final ProgramaAcademicoRepository programaAcademicoRepository;

    @Autowired
    public ProgramaAcademicoController(ProgramaAcademicoRepository programaAcademicoRepository) {
        this.programaAcademicoRepository = programaAcademicoRepository;
    }

    // Obtener todos los programas académicos
    @GetMapping
    public ResponseEntity<List<ProgramaAcademico>> getAllProgramaAcademicos() {
        List<ProgramaAcademico> programas = programaAcademicoRepository.findAll();
        return ResponseEntity.ok(programas);
    }

    // Crear nuevo programa académico
    @PostMapping
    public ResponseEntity<ProgramaAcademico> createProgramaAcademico(@RequestBody ProgramaAcademico programaAcademico) {
        ProgramaAcademico saved = programaAcademicoRepository.save(programaAcademico);
        return ResponseEntity.ok(saved);
    }

    // Obtener programa académico por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProgramaAcademico> getProgramaAcademicoById(@PathVariable Long id) {
        ProgramaAcademico programa = programaAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programa académico no existe con ID: " + id));
        return ResponseEntity.ok(programa);
    }

    // Actualizar programa académico
    @PutMapping("/{id}")
    public ResponseEntity<ProgramaAcademico> updateProgramaAcademico(@PathVariable Long id, @RequestBody ProgramaAcademico details) {
        ProgramaAcademico programa = programaAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programa académico no existe con ID: " + id));

        programa.setCodigoPrograma(details.getCodigoPrograma());
        programa.setNombrePrograma(details.getNombrePrograma());
        programa.setPensums(details.getPensums());
        programa.setDescripcion(details.getDescripcion());
        programa.setEstado(details.isEstado());
        programa.setFacultad(details.getFacultad());
        programa.setCreditosPrograma(details.getCreditosPrograma());
        programa.setCursos(details.getCursos());

        ProgramaAcademico updated = programaAcademicoRepository.save(programa);
        return ResponseEntity.ok(updated);
    }

    // Eliminar programa académico
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProgramaAcademico(@PathVariable Long id) {
        ProgramaAcademico programa = programaAcademicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programa académico no existe con ID: " + id));

        programaAcademicoRepository.delete(programa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Obtener programas académicos por ID de facultad
    @GetMapping("/facultad/{id}")
    public ResponseEntity<List<ProgramaAcademico>> getProgramaAcademicoByFacultadId(@PathVariable Long id) {
        List<ProgramaAcademico> programas = programaAcademicoRepository.findProgramaAcademicoByFacultadId(id);
        return ResponseEntity.ok(programas);
    }
}
