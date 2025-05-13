```java
package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.DocenteDTO;
import co.edu.udes.backend.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/docentes")
@CrossOrigin(origins = "*")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> getAllDocentes() {
        return ResponseEntity.ok(docenteService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> createDocente(@RequestBody DocenteDTO docenteDTO) {
        return ResponseEntity.ok(docenteService.crearDocente(docenteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getDocenteById(@PathVariable Long id) {
        return ResponseEntity.ok(docenteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> updateDocente(
            @PathVariable Long id,
            @RequestBody DocenteDTO docenteDTO) {
        return ResponseEntity.ok(docenteService.actualizarDocente(id, docenteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDocente(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
```