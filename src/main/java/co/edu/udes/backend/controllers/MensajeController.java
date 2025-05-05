package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Mensaje;
import co.edu.udes.backend.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Enviar mensaje
    @PostMapping
    public ResponseEntity<Mensaje> enviarMensaje(@RequestBody Mensaje mensaje) {
        return ResponseEntity.ok(mensajeService.enviarMensaje(mensaje));
    }

    // Obtener mensajes recibidos
    @GetMapping("/recibidos/{usuarioId}")
    public ResponseEntity<List<Mensaje>> obtenerRecibidos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(mensajeService.obtenerRecibidos(usuarioId));
    }

    // Obtener mensajes enviados
    @GetMapping("/enviados/{usuarioId}")
    public ResponseEntity<List<Mensaje>> obtenerEnviados(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(mensajeService.obtenerEnviados(usuarioId));
    }

    // Marcar como leído
    @PutMapping("/{id}/leido")
    public ResponseEntity<Mensaje> marcarComoLeido(@PathVariable Long id) {
        return ResponseEntity.ok(mensajeService.marcarComoLeido(id));
    }

    // Eliminar mensaje (opcional)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return ResponseEntity.ok().build();
    }
}
