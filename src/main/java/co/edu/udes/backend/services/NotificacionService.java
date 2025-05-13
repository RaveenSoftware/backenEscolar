package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.NotificacionDTO;
import co.edu.udes.backend.mappers.NotificacionMapper;
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
import java.util.stream.Collectors;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificacionMapper notificacionMapper;

    public NotificacionDTO enviarNotificacion(NotificacionDTO notificacionDTO) {
        Notificacion notificacion = notificacionMapper.toEntity(notificacionDTO);
        notificacion.setFechaEnvio(LocalDateTime.now());

        try {
            MimeMessage correo = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(correo, true);
            helper.setTo(notificacion.getDestinatario());
            helper.setSubject(notificacion.getAsunto());
            helper.setText(notificacion.getMensaje(), true);
            mailSender.send(correo);
            notificacion.setEnviado(true);
        } catch (MessagingException e) {
            notificacion.setEnviado(false);
        }

        return notificacionMapper.toDTO(notificacionRepository.save(notificacion));
    }

    public List<NotificacionDTO> obtenerTodas() {
        return notificacionRepository.findAll().stream()
            .map(notificacionMapper::toDTO)
            .collect(Collectors.toList());
    }
}