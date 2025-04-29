package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Asistencia;
import co.edu.udes.backend.repositories.AsistenciaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1/")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    // Obtener todas las asistencias
    @GetMapping("/asistencias")
    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    // Crear una nueva asistencia
    @PostMapping("/asistencias")
    public Asistencia createAsistencia(@RequestBody Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    // Obtener asistencia por ID
    @GetMapping("/asistencias/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con id: " + id));
        return ResponseEntity.ok(asistencia);
    }

    // Actualizar asistencia
    @PutMapping("/asistencias/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistenciaDetails) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con id: " + id));

        asistencia.setEstudiante(asistenciaDetails.getEstudiante());
        asistencia.setCurso(asistenciaDetails.getCurso());
        asistencia.setFecha(asistenciaDetails.getFecha());
        asistencia.setEstado(asistenciaDetails.getEstado());

        Asistencia updatedAsistencia = asistenciaRepository.save(asistencia);
        return ResponseEntity.ok(updatedAsistencia);
    }

    // Eliminar asistencia
    @DeleteMapping("/asistencias/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsistencia(@PathVariable Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con id: " + id));

        asistenciaRepository.delete(asistencia);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
