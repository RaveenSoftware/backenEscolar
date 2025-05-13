package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.PoligrafoDTO;
import co.edu.udes.backend.services.PoligrafoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/poligrafos")
@CrossOrigin(origins = "*")
public class PoligrafoController {

    @Autowired
    private PoligrafoService poligrafoService;

    @GetMapping
    public ResponseEntity<List<PoligrafoDTO>> getAllPoligrafos() {
        return ResponseEntity.ok(poligrafoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<PoligrafoDTO> createPoligrafo(@RequestBody PoligrafoDTO poligrafoDTO) {
        return ResponseEntity.ok(poligrafoService.crearPoligrafo(poligrafoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoligrafoDTO> getPoligrafoById(@PathVariable Long id) {
        return ResponseEntity.ok(poligrafoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoligrafoDTO> updatePoligrafo(
            @PathVariable Long id,
            @RequestBody PoligrafoDTO poligrafoDTO) {
        return ResponseEntity.ok(poligrafoService.actualizarPoligrafo(id, poligrafoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePoligrafo(@PathVariable Long id) {
        poligrafoService.eliminarPoligrafo(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}