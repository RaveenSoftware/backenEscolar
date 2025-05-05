package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Notificacion;
import co.edu.udes.backend.repositories.NotificacionRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Notificacion enviarNotificacion(String destinatario, String asunto, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setDestinatario(destinatario);
        notificacion.setAsunto(asunto);
        notificacion.setMensaje(mensaje);
        notificacion.setFechaEnvio(LocalDateTime.now());

        try {
            MimeMessage correo = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(correo, true);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(mensaje, true); // permite HTML
            mailSender.send(correo);
            notificacion.setEnviado(true);
        } catch (MessagingException e) {
            notificacion.setEnviado(false);
        }

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }
}
