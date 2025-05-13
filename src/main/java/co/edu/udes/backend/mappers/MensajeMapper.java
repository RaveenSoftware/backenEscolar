package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.MensajeDTO;
import co.edu.udes.backend.models.Mensaje;
import org.springframework.stereotype.Component;

@Component
public class MensajeMapper {

    public MensajeDTO toDTO(Mensaje mensaje) {
        if (mensaje == null) {
            return null;
        }

        MensajeDTO dto = new MensajeDTO();
        dto.setId(mensaje.getId());
        dto.setAsunto(mensaje.getAsunto());
        dto.setContenido(mensaje.getContenido());
        dto.setFechaEnvio(mensaje.getFechaEnvio());
        dto.setLeido(mensaje.isLeido());

        if (mensaje.getRemitente() != null) {
            dto.setRemitenteId(mensaje.getRemitente().getId());
        }

        if (mensaje.getDestinatario() != null) {
            dto.setDestinatarioId(mensaje.getDestinatario().getId());
        }

        return dto;
    }

    public Mensaje toEntity(MensajeDTO dto) {
        if (dto == null) {
            return null;
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setId(dto.getId());
        mensaje.setAsunto(dto.getAsunto());
        mensaje.setContenido(dto.getContenido());
        mensaje.setFechaEnvio(dto.getFechaEnvio());
        mensaje.setLeido(dto.isLeido());

        return mensaje;
    }
}