package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.NotificacionDTO;
import co.edu.udes.backend.models.Notificacion;
import org.springframework.stereotype.Component;

@Component
public class NotificacionMapper {

    public NotificacionDTO toDTO(Notificacion notificacion) {
        if (notificacion == null) {
            return null;
        }

        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(notificacion.getId());
        dto.setDestinatario(notificacion.getDestinatario());
        dto.setAsunto(notificacion.getAsunto());
        dto.setMensaje(notificacion.getMensaje());
        dto.setFechaEnvio(notificacion.getFechaEnvio());
        dto.setEnviado(notificacion.isEnviado());

        return dto;
    }

    public Notificacion toEntity(NotificacionDTO dto) {
        if (dto == null) {
            return null;
        }

        Notificacion notificacion = new Notificacion();
        notificacion.setId(dto.getId());
        notificacion.setDestinatario(dto.getDestinatario());
        notificacion.setAsunto(dto.getAsunto());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setFechaEnvio(dto.getFechaEnvio());
        notificacion.setEnviado(dto.isEnviado());

        return notificacion;
    }
}