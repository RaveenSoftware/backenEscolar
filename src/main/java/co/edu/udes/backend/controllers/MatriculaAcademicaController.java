package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.MatriculaAcademica;
import co.edu.udes.backend.services.MatriculaAcademicaService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
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

    // Crear nueva matrícula
    @PostMapping
    public ResponseEntity<MatriculaAcademica> crearMatricula(@RequestBody MatriculaAcademica matricula) {
        MatriculaAcademica nueva = matriculaService.registrarMatricula(matricula);
        return ResponseEntity.ok(nueva);
    }

    // Listar todas las matrículas
    @GetMapping
    public ResponseEntity<List<MatriculaAcademica>> listarTodas() {
        return ResponseEntity.ok(matriculaService.obtenerTodas());
    }

    // Obtener una matrícula por ID
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.obtenerPorId(id));
    }

    // Actualizar matrícula
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> actualizarMatricula(
            @PathVariable Long id,
            @RequestBody MatriculaAcademica detalles
    ) {
        MatriculaAcademica actualizada = matriculaService.actualizarMatricula(id, detalles);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar matrícula
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //  Inscribir un curso a una matrícula
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

    //  Cancelar (eliminar) un curso de una matrícula
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
