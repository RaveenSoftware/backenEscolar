package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Horario;
import co.edu.udes.backend.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public List<Horario> getAllHorarios() {
        return horarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        Horario horario = horarioService.obtenerPorId(id);
        return horario != null ? ResponseEntity.ok(horario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createHorario(@RequestBody Horario horario) {
        try {
            Horario nuevo = horarioService.crearHorario(horario);
            return ResponseEntity.ok(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHorario(@PathVariable Long id, @RequestBody Horario horario) {
        try {
            Horario actualizado = horarioService.actualizarHorario(id, horario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHorario(@PathVariable Long id) {
        horarioService.eliminarHorario(id);
        return ResponseEntity.ok().build();
    }
}
