package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Horario;
import co.edu.udes.backend.repositories.HorarioRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    private final HorarioRepository horarioRepository;

    @Autowired
    public HorarioController(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    // Obtener todos los horarios
    @GetMapping
    public ResponseEntity<List<Horario>> getAllHorarios() {
        List<Horario> horarios = horarioRepository.findAll();
        return ResponseEntity.ok(horarios);
    }

    // Crear nuevo horario
    @PostMapping
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
        Horario savedHorario = horarioRepository.save(horario);
        return ResponseEntity.ok(savedHorario);
    }

    // Obtener horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario no existe con ID: " + id));
        return ResponseEntity.ok(horario);
    }

    // Actualizar horario existente
    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @RequestBody Horario horarioDetails) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario no existe con ID: " + id));

        horario.setHoraInicio(horarioDetails.getHoraInicio());
        horario.setHoraFinalizacion(horarioDetails.getHoraFinalizacion());
        horario.setDia(horarioDetails.getDia());
        horario.setEstado(horarioDetails.isEstado());

        Horario updatedHorario = horarioRepository.save(horario);
        return ResponseEntity.ok(updatedHorario);
    }

    // Eliminar horario
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteHorario(@PathVariable Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario no existe con ID: " + id));

        horarioRepository.delete(horario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
