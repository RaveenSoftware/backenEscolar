package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Mensaje;
import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.repositories.MensajeRepository;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private PersonaRepository personaRepository;

    //  Usado por el controller directamente con objeto completo
    public Mensaje enviarMensaje(Mensaje mensaje) {
        Persona remitente = personaRepository.findById(mensaje.getRemitente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Remitente no encontrado"));

        Persona destinatario = personaRepository.findById(mensaje.getDestinatario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Destinatario no encontrado"));

        mensaje.setRemitente(remitente);
        mensaje.setDestinatario(destinatario);
        mensaje.setFechaEnvio(LocalDateTime.now()); //  CORREGIDO
        mensaje.setLeido(false);

        return mensajeRepository.save(mensaje);
    }

    //  Obtener mensajes recibidos por ID de usuario
    public List<Mensaje> obtenerRecibidos(Long usuarioId) {
        return mensajeRepository.findByDestinatarioId(usuarioId);
    }

    //  Obtener mensajes enviados por ID de usuario
    public List<Mensaje> obtenerEnviados(Long usuarioId) {
        return mensajeRepository.findByRemitenteId(usuarioId);
    }

    //  Marcar mensaje como leído
    public Mensaje marcarComoLeido(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje no encontrado con ID: " + id));
        mensaje.setLeido(true);
        return mensajeRepository.save(mensaje);
    }

    //  Eliminar mensaje
    public void eliminarMensaje(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje no encontrado con ID: " + id));
        mensajeRepository.delete(mensaje);
    }
}
