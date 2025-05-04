package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Asistencia;
import co.edu.udes.backend.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    // Crear asistencia
    @PostMapping
    public ResponseEntity<Asistencia> registrarAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia nueva = asistenciaService.registrarAsistencia(asistencia);
        return ResponseEntity.ok(nueva);
    }

    // Obtener todas las asistencias
    @GetMapping
    public ResponseEntity<List<Asistencia>> getAllAsistencias() {
        return ResponseEntity.ok(asistenciaService.obtenerTodas());
    }

    // Obtener asistencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        return ResponseEntity.ok(asistenciaService.obtenerPorId(id));
    }

    // Actualizar asistencia
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        Asistencia actualizada = asistenciaService.actualizarAsistencia(id, asistencia);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar asistencia
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsistencia(@PathVariable Long id) {
        asistenciaService.eliminarAsistencia(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
