```java
package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.CursoDTO;
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

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        return ResponseEntity.ok(cursoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok(cursoService.crearCurso(cursoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(
            @PathVariable Long id,
            @RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, cursoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
```