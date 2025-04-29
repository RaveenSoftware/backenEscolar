package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Poligrafo;
import co.edu.udes.backend.repositories.PoligrafoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/historial")
@CrossOrigin(origins = "*")
public class HistorialAcademicoController {

    @Autowired
    private PoligrafoRepository poligrafoRepository;

    // Obtener todos los registros
    @GetMapping("/poligrafos")
    public List<Poligrafo> getAllPoligrafos() {
        return poligrafoRepository.findAll();
    }

    // Crear un nuevo registro
    @PostMapping("/poligrafos")
    public Poligrafo createPoligrafo(@RequestBody Poligrafo poligrafo) {
        return poligrafoRepository.save(poligrafo);
    }

    // Obtener por ID
    @GetMapping("/poligrafos/{id}")
    public ResponseEntity<Poligrafo> getPoligrafoById(@PathVariable Long id) {
        Poligrafo poligrafo = poligrafoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no encontrado con ID: " + id));
        return ResponseEntity.ok(poligrafo);
    }

    // Actualizar registro
    @PutMapping("/poligrafos/{id}")
    public ResponseEntity<Poligrafo> updatePoligrafo(@PathVariable Long id, @RequestBody Poligrafo details) {
        Poligrafo poligrafo = poligrafoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no encontrado con ID: " + id));

        poligrafo.setEstudiante(details.getEstudiante());
        poligrafo.setAsignatura(details.getAsignatura());
        poligrafo.setCalificaciones(details.getCalificaciones());
        poligrafo.setFechaEmision(details.getFechaEmision());
        poligrafo.setSemestreAcademico(details.getSemestreAcademico());
        poligrafo.setCreditosMatriculados(details.getCreditosMatriculados());
        poligrafo.setPromedio(details.getPromedio());
        poligrafo.setCreditosAcumulados(details.getCreditosAcumulados());
        poligrafo.setPromedioAcumulado(details.getPromedioAcumulado());

        Poligrafo updated = poligrafoRepository.save(poligrafo);
        return ResponseEntity.ok(updated);
    }

    // Eliminar registro
    @DeleteMapping("/poligrafos/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePoligrafo(@PathVariable Long id) {
        Poligrafo poligrafo = poligrafoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no encontrado con ID: " + id));

        poligrafoRepository.delete(poligrafo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }
}
