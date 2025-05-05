package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.ReservaEspacio;
import co.edu.udes.backend.services.ReservaEspacioService;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaEspacioController {

    @Autowired
    private ReservaEspacioService reservaEspacioService;

    //  Crear reserva
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaEspacio reserva) {
        try {
            ReservaEspacio nueva = reservaEspacioService.crearReserva(reserva);
            return ResponseEntity.ok(nueva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    //  Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<ReservaEspacio>> obtenerTodas() {
        return ResponseEntity.ok(reservaEspacioService.obtenerTodas());
    }

    //  Obtener reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaEspacio> obtenerPorId(@PathVariable Long id) {
        ReservaEspacio reserva = reservaEspacioService.obtenerPorId(id);
        return ResponseEntity.ok(reserva);
    }

    //  Actualizar reserva
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@PathVariable Long id, @RequestBody ReservaEspacio nueva) {
        try {
            ReservaEspacio actualizada = reservaEspacioService.actualizarReserva(id, nueva);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    //  Eliminar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        reservaEspacioService.eliminarReserva(id);
        return ResponseEntity.ok(Map.of("deleted", true));
    }

    //  Aprobar una reserva
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<ReservaEspacio> aprobar(@PathVariable Long id) {
        ReservaEspacio aprobada = reservaEspacioService.aprobarReserva(id);
        return ResponseEntity.ok(aprobada);
    }

    //  Rechazar una reserva
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<ReservaEspacio> rechazar(@PathVariable Long id) {
        ReservaEspacio rechazada = reservaEspacioService.rechazarReserva(id);
        return ResponseEntity.ok(rechazada);
    }
}
