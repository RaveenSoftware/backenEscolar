package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.TipoDocumento;
import co.edu.udes.backend.repositories.TipoDocumentoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tipos-documentos")
@CrossOrigin(origins = "*")
public class TipoDocumentoController {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    public TipoDocumentoController(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    // Obtener todos los tipos de documento
    @GetMapping
    public ResponseEntity<List<TipoDocumento>> getAllTiposDocumento() {
        List<TipoDocumento> tipos = tipoDocumentoRepository.findAll();
        return ResponseEntity.ok(tipos);
    }

    // Crear nuevo tipo de documento
    @PostMapping
    public ResponseEntity<TipoDocumento> createTipoDocumento(@RequestBody TipoDocumento tipoDocumento) {
        TipoDocumento saved = tipoDocumentoRepository.save(tipoDocumento);
        return ResponseEntity.ok(saved);
    }

    // Obtener tipo de documento por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumento> getTipoDocumentoById(@PathVariable Long id) {
        TipoDocumento tipo = tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no existe con ID: " + id));
        return ResponseEntity.ok(tipo);
    }

    // Actualizar tipo de documento
    @PutMapping("/{id}")
    public ResponseEntity<TipoDocumento> updateTipoDocumento(@PathVariable Long id, @RequestBody TipoDocumento tipoDetails) {
        TipoDocumento tipo = tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no existe con ID: " + id));

        tipo.setNombre(tipoDetails.getNombre());
        tipo.setEstado(tipoDetails.isEstado());

        TipoDocumento updated = tipoDocumentoRepository.save(tipo);
        return ResponseEntity.ok(updated);
    }

    // Eliminar tipo de documento
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTipoDocumento(@PathVariable Long id) {
        TipoDocumento tipo = tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no existe con ID: " + id));

        tipoDocumentoRepository.delete(tipo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
