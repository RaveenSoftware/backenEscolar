package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Material;
import co.edu.udes.backend.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materiales")
@CrossOrigin(origins = "*")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // ✅ Crear material
    @PostMapping
    public ResponseEntity<Material> crear(@RequestBody Material material) {
        return ResponseEntity.ok(materialService.crearMaterial(material));
    }

    //  Listar todos
    @GetMapping
    public ResponseEntity<List<Material>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    //  Listar solo disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Material>> listarDisponibles() {
        return ResponseEntity.ok(materialService.listarDisponibles());
    }

    //  Obtener uno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Material> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.obtenerPorId(id));
    }

    //  Actualizar material
    @PutMapping("/{id}")
    public ResponseEntity<Material> actualizar(@PathVariable Long id, @RequestBody Material nuevo) {
        return ResponseEntity.ok(materialService.actualizar(id, nuevo));
    }

    //  Eliminar material
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        materialService.eliminar(id);
        return ResponseEntity.ok(Map.of("deleted", true));
    }
}
