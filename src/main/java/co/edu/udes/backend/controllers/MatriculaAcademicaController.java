package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.MatriculaAcademicaDTO;
import co.edu.udes.backend.services.MatriculaAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaAcademicaController {

    @Autowired
    private MatriculaAcademicaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaAcademicaDTO>> getAllMatriculas() {
        return ResponseEntity.ok(matriculaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<MatriculaAcademicaDTO> createMatricula(@RequestBody MatriculaAcademicaDTO matriculaDTO) {
        return ResponseEntity.ok(matriculaService.registrarMatricula(matriculaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAcademicaDTO> getMatriculaById(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaAcademicaDTO> updateMatricula(
            @PathVariable Long id,
            @RequestBody MatriculaAcademicaDTO matriculaDTO) {
        return ResponseEntity.ok(matriculaService.actualizarMatricula(id, matriculaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMatricula(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{matriculaId}/cursos/{cursoId}")
    public ResponseEntity<MatriculaAcademicaDTO> inscribirCurso(
            @PathVariable Long matriculaId,
            @PathVariable Long cursoId) {
        return ResponseEntity.ok(matriculaService.inscribirCurso(matriculaId, cursoId));
    }

    @DeleteMapping("/{matriculaId}/cursos/{cursoId}")
    public ResponseEntity<MatriculaAcademicaDTO> eliminarCurso(
            @PathVariable Long matriculaId,
            @PathVariable Long cursoId) {
        return ResponseEntity.ok(matriculaService.eliminarCursoDeMatricula(matriculaId, cursoId));
    }
}