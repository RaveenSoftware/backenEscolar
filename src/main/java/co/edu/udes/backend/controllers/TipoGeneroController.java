package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.TipoGenero;
import co.edu.udes.backend.repositories.TipoGeneroRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tipos-generos")
@CrossOrigin(origins = "*")
public class TipoGeneroController {

    private final TipoGeneroRepository tipoGeneroRepository;

    @Autowired
    public TipoGeneroController(TipoGeneroRepository tipoGeneroRepository) {
        this.tipoGeneroRepository = tipoGeneroRepository;
    }

    // Obtener todos los tipos de género
    @GetMapping
    public ResponseEntity<List<TipoGenero>> getAllTiposGenero() {
        List<TipoGenero> lista = tipoGeneroRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    // Crear un nuevo tipo de género
    @PostMapping
    public ResponseEntity<TipoGenero> createTipoGenero(@RequestBody TipoGenero tipoGenero) {
        TipoGenero saved = tipoGeneroRepository.save(tipoGenero);
        return ResponseEntity.ok(saved);
    }

    // Obtener tipo de género por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoGenero> getTipoGeneroById(@PathVariable Long id) {
        TipoGenero genero = tipoGeneroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de género no existe con ID: " + id));
        return ResponseEntity.ok(genero);
    }

    // Actualizar tipo de género
    @PutMapping("/{id}")
    public ResponseEntity<TipoGenero> updateTipoGenero(@PathVariable Long id, @RequestBody TipoGenero generoDetails) {
        TipoGenero genero = tipoGeneroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de género no existe con ID: " + id));

        genero.setNombre(generoDetails.getNombre());
        genero.setEstado(generoDetails.isEstado());

        TipoGenero updated = tipoGeneroRepository.save(genero);
        return ResponseEntity.ok(updated);
    }

    // Eliminar tipo de género
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTipoGenero(@PathVariable Long id) {
        TipoGenero genero = tipoGeneroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de género no existe con ID: " + id));

        tipoGeneroRepository.delete(genero);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
