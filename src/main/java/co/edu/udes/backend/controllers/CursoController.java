package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Crear curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevo = cursoService.crearCurso(curso);
        return ResponseEntity.ok(nuevo);
    }

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        List<Curso> lista = cursoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        return ResponseEntity.ok(curso);
    }

    // Actualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso cursoActualizado) {
        Curso actualizado = cursoService.actualizarCurso(id, cursoActualizado);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
