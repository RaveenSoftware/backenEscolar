package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Asistencia;
import co.edu.udes.backend.repositories.AsistenciaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    private final AsistenciaRepository asistenciaRepository;

    @Autowired
    public AsistenciaController(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    // Obtener todas las asistencias
    @GetMapping
    public ResponseEntity<List<Asistencia>> getAllAsistencias() {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        return ResponseEntity.ok(asistencias);
    }

    // Crear una nueva asistencia
    @PostMapping
    public ResponseEntity<Asistencia> createAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia savedAsistencia = asistenciaRepository.save(asistencia);
        return ResponseEntity.ok(savedAsistencia);
    }

    // Obtener asistencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con ID: " + id));
        return ResponseEntity.ok(asistencia);
    }

    // Actualizar asistencia existente
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistenciaDetails) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con ID: " + id));

        asistencia.setEstudiante(asistenciaDetails.getEstudiante());
        asistencia.setCurso(asistenciaDetails.getCurso());
        asistencia.setFecha(asistenciaDetails.getFecha());
        asistencia.setEstado(asistenciaDetails.getEstado());

        Asistencia updatedAsistencia = asistenciaRepository.save(asistencia);
        return ResponseEntity.ok(updatedAsistencia);
    }

    // Eliminar asistencia
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsistencia(@PathVariable Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con ID: " + id));

        asistenciaRepository.delete(asistencia);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
