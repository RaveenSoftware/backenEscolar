package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.services.DocenteCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/docente-curso")
@CrossOrigin(origins = "*")
public class DocenteCursoController {

    @Autowired
    private DocenteCursoService docenteCursoService;

    // Asignar curso a un docente
    @PostMapping("/{docenteId}/asignar/{cursoId}")
    public ResponseEntity<Docente> asignarCurso(@PathVariable Long docenteId, @PathVariable Long cursoId) {
        Docente docenteActualizado = docenteCursoService.asignarCurso(docenteId, cursoId);
        return ResponseEntity.ok(docenteActualizado);
    }

    // Obtener cursos asignados a un docente
    @GetMapping("/{docenteId}/cursos")
    public ResponseEntity<Set<Curso>> obtenerCursosPorDocente(@PathVariable Long docenteId) {
        Set<Curso> cursos = docenteCursoService.obtenerCursosPorDocente(docenteId);
        return ResponseEntity.ok(cursos);
    }

    // Eliminar asignación de un curso a un docente
    @DeleteMapping("/{docenteId}/eliminar/{cursoId}")
    public ResponseEntity<Map<String, Boolean>> eliminarCurso(@PathVariable Long docenteId, @PathVariable Long cursoId) {
        docenteCursoService.eliminarCurso(docenteId, cursoId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
