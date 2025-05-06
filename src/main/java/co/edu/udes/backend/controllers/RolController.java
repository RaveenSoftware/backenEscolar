package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Rol;
import co.edu.udes.backend.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {

    @Autowired
    private RolService rolService;

    // ✅ Crear un nuevo rol
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol creado = rolService.crearRol(rol);
        return ResponseEntity.ok(creado);
    }

    // ✅ Listar todos los roles
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Rol>> obtenerTodos() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }

    // ✅ Obtener un rol por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Long id) {
        Rol rol = rolService.obtenerPorId(id);
        return ResponseEntity.ok(rol);
    }

    // ✅ Actualizar un rol
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @RequestBody Rol nuevo) {
        Rol actualizado = rolService.actualizar(id, nuevo);
        return ResponseEntity.ok(actualizado);
    }

    // ✅ Eliminar un rol
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
        return ResponseEntity.ok(Map.of("eliminado", true));
    }
}
