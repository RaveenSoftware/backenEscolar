package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.MatriculaAcademica;
import co.edu.udes.backend.services.MatriculaAcademicaService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // ADMIN o ESTUDIANTE puede registrar matrícula
    @PreAuthorize("hasAnyRole('ADMIN', 'ESTUDIANTE')")
    @PostMapping
    public ResponseEntity<MatriculaAcademica> crearMatricula(@RequestBody MatriculaAcademica matricula) {
        MatriculaAcademica nueva = matriculaService.registrarMatricula(matricula);
        return ResponseEntity.ok(nueva);
    }

    // Solo ADMIN puede ver todas las matrículas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<MatriculaAcademica>> listarTodas() {
        return ResponseEntity.ok(matriculaService.obtenerTodas());
    }

    // ADMIN y ESTUDIANTE pueden consultar (validar en service si el estudiante consulta solo la suya)
    @PreAuthorize("hasAnyRole('ADMIN', 'ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.obtenerPorId(id));
    }

    // Solo ADMIN puede actualizar una matrícula académica
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> actualizarMatricula(
            @PathVariable Long id,
            @RequestBody MatriculaAcademica detalles
    ) {
        MatriculaAcademica actualizada = matriculaService.actualizarMatricula(id, detalles);
        return ResponseEntity.ok(actualizada);
    }

    // Solo ADMIN puede eliminar matrícula
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // ADMIN o ESTUDIANTE puede inscribir curso a su matrícula
    @PreAuthorize("hasAnyRole('ADMIN', 'ESTUDIANTE')")
    @PostMapping("/{matriculaId}/cursos/{cursoId}")
    public ResponseEntity<?> inscribirCurso(
            @PathVariable Long matriculaId,
            @PathVariable Long cursoId
    ) {
        try {
            MatriculaAcademica actualizada = matriculaService.inscribirCurso(matriculaId, cursoId);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalStateException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ADMIN o ESTUDIANTE puede cancelar curso (se puede validar en service si es suyo)
    @PreAuthorize("hasAnyRole('ADMIN', 'ESTUDIANTE')")
    @DeleteMapping("/{matriculaId}/cursos/{cursoId}")
    public ResponseEntity<?> eliminarCurso(
            @PathVariable Long matriculaId,
            @PathVariable Long cursoId
    ) {
        try {
            MatriculaAcademica actualizada = matriculaService.eliminarCursoDeMatricula(matriculaId, cursoId);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalStateException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
