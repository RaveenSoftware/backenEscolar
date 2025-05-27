package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dtos.EstudianteDTO;
import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.services.EstudianteService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Obtener todos los estudiantes (DTOs)
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.obtenerTodosDTOs());
    }

    // Crear estudiante usando DTO
    @PostMapping
    public ResponseEntity<?> createEstudiante(@RequestBody EstudianteDTO dto) {
        try {
            Estudiante creado = estudianteService.registrarEstudianteDesdeDTO(dto);
            return ResponseEntity.ok(estudianteService.convertirADTO(creado));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Obtener estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        return ResponseEntity.ok(estudianteService.convertirADTO(estudiante));
    }

    // Actualizar estudiante (se mantiene con entidad completa para facilitar validaciones)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        try {
            Estudiante actualizado = estudianteService.actualizarEstudiante(id, estudianteDetails);
            return ResponseEntity.ok(estudianteService.convertirADTO(actualizado));
        } catch (ResourceNotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Eliminar estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
