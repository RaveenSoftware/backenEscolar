package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Solo ADMIN puede crear
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Docente> crearDocente(@RequestBody Docente docente) {
        Docente nuevo = docenteService.crearDocente(docente);
        return ResponseEntity.ok(nuevo);
    }

    // ADMIN y DOCENTE pueden ver todos
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    public ResponseEntity<List<Docente>> obtenerTodos() {
        return ResponseEntity.ok(docenteService.obtenerTodos());
    }

    // ADMIN y DOCENTE pueden ver uno
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    public ResponseEntity<Docente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(docenteService.obtenerPorId(id));
    }

    // Solo ADMIN puede actualizar
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Docente> actualizarDocente(@PathVariable Long id, @RequestBody Docente docente) {
        Docente actualizado = docenteService.actualizarDocente(id, docente);
        return ResponseEntity.ok(actualizado);
    }

    // Solo ADMIN puede eliminar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> eliminarDocente(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
