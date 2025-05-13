package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.MensajeDTO;
import co.edu.udes.backend.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping
    public ResponseEntity<MensajeDTO> enviarMensaje(@RequestBody MensajeDTO mensajeDTO) {
        return ResponseEntity.ok(mensajeService.enviarMensaje(mensajeDTO));
    }

    @GetMapping("/recibidos/{usuarioId}")
    public ResponseEntity<List<MensajeDTO>> obtenerRecibidos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(mensajeService.obtenerRecibidos(usuarioId));
    }

    @GetMapping("/enviados/{usuarioId}")
    public ResponseEntity<List<MensajeDTO>> obtenerEnviados(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(mensajeService.obtenerEnviados(usuarioId));
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<MensajeDTO> marcarComoLeido(@PathVariable Long id) {
        return ResponseEntity.ok(mensajeService.marcarComoLeido(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}