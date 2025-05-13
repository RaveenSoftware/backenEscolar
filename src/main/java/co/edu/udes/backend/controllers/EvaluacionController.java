package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.EvaluacionDTO;
import co.edu.udes.backend.services.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<EvaluacionDTO>> getAllEvaluaciones() {
        return ResponseEntity.ok(evaluacionService.listarEvaluaciones());
    }

    @PostMapping
    public ResponseEntity<EvaluacionDTO> createEvaluacion(@RequestBody EvaluacionDTO evaluacionDTO) {
        return ResponseEntity.ok(evaluacionService.crearEvaluacion(evaluacionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> getEvaluacionById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> updateEvaluacion(
            @PathVariable Long id,
            @RequestBody EvaluacionDTO evaluacionDTO) {
        return ResponseEntity.ok(evaluacionService.actualizarEvaluacion(id, evaluacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvaluacion(@PathVariable Long id) {
        evaluacionService.eliminarEvaluacion(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}