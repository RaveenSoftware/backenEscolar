package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Poligrafo;
import co.edu.udes.backend.repositories.PoligrafoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/poligrafos")
@CrossOrigin(origins = "*")
public class PoligrafoController {

    private final PoligrafoRepository poligrafoRepository;

    @Autowired
    public PoligrafoController(PoligrafoRepository poligrafoRepository) {
        this.poligrafoRepository = poligrafoRepository;
    }

    // Obtener todos los registros de polígrafos
    @GetMapping
    public ResponseEntity<List<Poligrafo>> getAllPoligrafos() {
        List<Poligrafo> poligrafos = poligrafoRepository.findAll();
        return ResponseEntity.ok(poligrafos);
    }

    // Crear un nuevo poligrafo
    @PostMapping
    public ResponseEntity<Poligrafo> createPoligrafo(@RequestBody Poligrafo poligrafo) {
        Poligrafo saved = poligrafoRepository.save(poligrafo);
        return ResponseEntity.ok(saved);
    }

    // Obtener un poligrafo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Poligrafo> getPoligrafoById(@PathVariable Long id) {
        Poligrafo poligrafo = poligrafoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no existe con ID: " + id));
        return ResponseEntity.ok(poligrafo);
    }

    // Actualizar un poligrafo existente
    @PutMapping("/{id}")
    public ResponseEntity<Poligrafo> updatePoligrafo(@PathVariable Long id, @RequestBody Poligrafo poligrafoDetails) {
        Poligrafo poligrafo = poligrafoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no existe con ID: " + id));

        poligrafo.setEstudiante(poligrafoDetails.getEstudiante());
        poligrafo.setAsignatura(poligrafoDetails.getAsignatura());
        poligrafo.setCalificaciones(poligrafoDetails.getCalificaciones());
        poligrafo.setFechaEmision(poligrafoDetails.getFechaEmision());
        poligrafo.setSemestreAcademico(poligrafoDetails.getSemestreAcademico());
        poligrafo.setCreditosMatriculados(poligrafoDetails.getCreditosMatriculados());
        poligrafo.setPromedio(poligrafoDetails.getPromedio());
        poligrafo.setCreditosAcumulados(poligrafoDetails.getCreditosAcumulados());
        poligrafo.setPromedioAcumulado(poligrafoDetails.getPromedioAcumulado());

        Poligrafo updated = poligrafoRepository.save(poligrafo);
        return ResponseEntity.ok(updated);
    }

    // Eliminar un poligrafo
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePoligrafo(@PathVariable Long id) {
        Poligrafo poligrafo = poligrafoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no existe con ID: " + id));

        poligrafoRepository.delete(poligrafo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
