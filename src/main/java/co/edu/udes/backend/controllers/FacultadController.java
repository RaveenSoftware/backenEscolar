package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Facultad;
import co.edu.udes.backend.repositories.FacultadRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/facultades")
@CrossOrigin(origins = "*")
public class FacultadController {

    private final FacultadRepository facultadRepository;

    @Autowired
    public FacultadController(FacultadRepository facultadRepository) {
        this.facultadRepository = facultadRepository;
    }

    // Solo ADMIN puede obtener todas las facultades
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Facultad>> getAllFacultades() {
        List<Facultad> facultades = facultadRepository.findAll();
        return ResponseEntity.ok(facultades);
    }

    // Solo ADMIN puede crear
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Facultad> createFacultad(@RequestBody Facultad facultad) {
        Facultad savedFacultad = facultadRepository.save(facultad);
        return ResponseEntity.ok(savedFacultad);
    }

    // Solo ADMIN puede consultar por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Facultad> getFacultadById(@PathVariable Long id) {
        Facultad facultad = facultadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facultad no existe con ID: " + id));
        return ResponseEntity.ok(facultad);
    }

    // Solo ADMIN puede actualizar
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable Long id, @RequestBody Facultad facultadDetails) {
        Facultad facultad = facultadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facultad no existe con ID: " + id));

        facultad.setNombre(facultadDetails.getNombre());
        facultad.setEstado(facultadDetails.isEstado());

        Facultad updatedFacultad = facultadRepository.save(facultad);
        return ResponseEntity.ok(updatedFacultad);
    }

    // Solo ADMIN puede eliminar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteFacultad(@PathVariable Long id) {
        Facultad facultad = facultadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facultad no existe con ID: " + id));

        facultadRepository.delete(facultad);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
