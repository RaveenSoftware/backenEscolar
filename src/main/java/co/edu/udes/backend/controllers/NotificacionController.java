package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Notificacion;
import co.edu.udes.backend.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // ✅ Enviar una notificación por correo
    @PostMapping
    public ResponseEntity<Notificacion> enviarNotificacion(@RequestBody Map<String, String> payload) {
        String destinatario = payload.get("destinatario");
        String asunto = payload.get("asunto");
        String mensaje = payload.get("mensaje");

        Notificacion enviada = notificacionService.enviarNotificacion(destinatario, asunto, mensaje);
        return ResponseEntity.ok(enviada);
    }

    // ✅ Listar todas las notificaciones
    @GetMapping
    public ResponseEntity<List<Notificacion>> listarTodas() {
        return ResponseEntity.ok(notificacionService.obtenerTodas());
    }
}
