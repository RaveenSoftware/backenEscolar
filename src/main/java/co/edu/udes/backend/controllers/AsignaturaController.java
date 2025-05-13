package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AsignaturaDTO;
import co.edu.udes.backend.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/asignaturas")
@CrossOrigin(origins = "*")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public ResponseEntity<List<AsignaturaDTO>> getAllAsignaturas() {
        return ResponseEntity.ok(asignaturaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<AsignaturaDTO> createAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        return ResponseEntity.ok(asignaturaService.crearAsignatura(asignaturaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> getAsignaturaById(@PathVariable Long id) {
        return ResponseEntity.ok(asignaturaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> updateAsignatura(
            @PathVariable Long id,
            @RequestBody AsignaturaDTO asignaturaDTO) {
        return ResponseEntity.ok(asignaturaService.actualizarAsignatura(id, asignaturaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsignatura(@PathVariable Long id) {
        asignaturaService.eliminarAsignatura(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}