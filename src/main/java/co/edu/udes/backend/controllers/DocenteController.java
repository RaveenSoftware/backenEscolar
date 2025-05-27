package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.services.DocenteService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/docentes")
@CrossOrigin(origins = "*")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    // Crear nuevo docente
    @PostMapping
    public ResponseEntity<?> crearDocente(@RequestBody Docente docente) {
        try {
            return ResponseEntity.ok(docenteService.crearDocente(docente));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Obtener todos los docentes
    @GetMapping
    public ResponseEntity<List<Docente>> obtenerTodos() {
        return ResponseEntity.ok(docenteService.obtenerTodos());
    }

    // Obtener docente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Docente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(docenteService.obtenerPorId(id));
    }

    // Actualizar docente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDocente(@PathVariable Long id, @RequestBody Docente docente) {
        try {
            return ResponseEntity.ok(docenteService.actualizarDocente(id, docente));
        } catch (ResourceNotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Eliminar docente
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarDocente(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
