package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.NotificacionDTO;
import co.edu.udes.backend.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionDTO> enviarNotificacion(@RequestBody NotificacionDTO notificacionDTO) {
        return ResponseEntity.ok(notificacionService.enviarNotificacion(notificacionDTO));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionDTO>> listarTodas() {
        return ResponseEntity.ok(notificacionService.obtenerTodas());
    }
}