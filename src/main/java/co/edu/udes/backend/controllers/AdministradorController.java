package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dtos.AdministradorDTO;
import co.edu.udes.backend.models.Administrador;
import co.edu.udes.backend.services.AdministradorService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // Obtener todos los administradores
    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> getAllAdministradores() {
        List<AdministradorDTO> dtoList = administradorService.obtenerTodos()
                .stream()
                .map(administradorService::convertirADTO)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    // Crear nuevo administrador
    @PostMapping
    public ResponseEntity<?> createAdministrador(@RequestBody Administrador administrador) {
        try {
            Administrador creado = administradorService.registrarAdministrador(administrador);
            return ResponseEntity.ok(administradorService.convertirADTO(creado));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Obtener administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> getAdministradorById(@PathVariable Long id) {
        Administrador admin = administradorService.obtenerPorId(id);
        return ResponseEntity.ok(administradorService.convertirADTO(admin));
    }

    // Actualizar administrador
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdministrador(@PathVariable Long id, @RequestBody Administrador detalles) {
        try {
            Administrador actualizado = administradorService.actualizarAdministrador(id, detalles);
            return ResponseEntity.ok(administradorService.convertirADTO(actualizado));
        } catch (ResourceNotFoundException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Eliminar administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
