package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.PrestamoMaterial;
import co.edu.udes.backend.services.PrestamoMaterialService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoMaterialController {

    @Autowired
    private PrestamoMaterialService prestamoMaterialService;

    //  Crear préstamo
    @PostMapping
    public ResponseEntity<PrestamoMaterial> crear(@RequestBody PrestamoMaterial prestamo) {
        PrestamoMaterial creado = prestamoMaterialService.crearPrestamo(prestamo);
        return ResponseEntity.ok(creado);
    }

    //  Obtener todos los préstamos
    @GetMapping
    public ResponseEntity<List<PrestamoMaterial>> listarTodos() {
        return ResponseEntity.ok(prestamoMaterialService.obtenerTodos());
    }

    //  Obtener préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoMaterial> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoMaterialService.obtenerPorId(id));
    }

    //  Actualizar préstamo
    @PutMapping("/{id}")
    public ResponseEntity<PrestamoMaterial> actualizar(@PathVariable Long id, @RequestBody PrestamoMaterial datos) {
        return ResponseEntity.ok(prestamoMaterialService.actualizarPrestamo(id, datos));
    }

    //  Eliminar préstamo
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        prestamoMaterialService.eliminarPrestamo(id);
        return ResponseEntity.ok(Map.of("eliminado", true));
    }

    //  Marcar como devuelto
    @PutMapping("/{id}/devolver")
    public ResponseEntity<PrestamoMaterial> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoMaterialService.marcarComoDevuelto(id));
    }

    // Opcional: listar por persona
    @GetMapping("/persona/{id}")
    public ResponseEntity<List<PrestamoMaterial>> listarPorPersona(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoMaterialService.obtenerPorPersona(id));
    }

    // Opcional: listar préstamos pendientes
    @GetMapping("/pendientes")
    public ResponseEntity<List<PrestamoMaterial>> listarPendientes() {
        return ResponseEntity.ok(prestamoMaterialService.obtenerPendientes());
    }
}
