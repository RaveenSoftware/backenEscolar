package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Docente;
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

    // Crear docente
    @PostMapping
    public ResponseEntity<Docente> crearDocente(@RequestBody Docente docente) {
        Docente nuevo = docenteService.crearDocente(docente);
        return ResponseEntity.ok(nuevo);
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
    public ResponseEntity<Docente> actualizarDocente(@PathVariable Long id, @RequestBody Docente docente) {
        Docente actualizado = docenteService.actualizarDocente(id, docente);
        return ResponseEntity.ok(actualizado);
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
