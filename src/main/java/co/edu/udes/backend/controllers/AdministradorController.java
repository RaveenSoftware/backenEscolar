package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Administrador;
import co.edu.udes.backend.repositories.AdministradorRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    private final AdministradorRepository administradorRepository;

    @Autowired
    public AdministradorController(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    // Obtener todos los administradores
    @GetMapping
    public ResponseEntity<List<Administrador>> getAllAdministradores() {
        List<Administrador> administradores = administradorRepository.findAll();
        return ResponseEntity.ok(administradores);
    }

    // Crear nuevo administrador
    @PostMapping
    public ResponseEntity<Administrador> createAdministrador(@RequestBody Administrador administrador) {
        Administrador savedAdministrador = administradorRepository.save(administrador);
        return ResponseEntity.ok(savedAdministrador);
    }

    // Obtener administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no existe con ID: " + id));
        return ResponseEntity.ok(administrador);
    }

    // Actualizar administrador existente
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Long id, @RequestBody Administrador administradorDetails) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no existe con ID: " + id));

        administrador.setFacultad(administradorDetails.getFacultad());
        administrador.setCodigoInstitucional(administradorDetails.getCodigoInstitucional());
        administrador.setCorreoInstitucional(administradorDetails.getCorreoInstitucional());

        Administrador updatedAdministrador = administradorRepository.save(administrador);
        return ResponseEntity.ok(updatedAdministrador);
    }

    // Eliminar administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAdministrador(@PathVariable Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no existe con ID: " + id));

        administradorRepository.delete(administrador);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
