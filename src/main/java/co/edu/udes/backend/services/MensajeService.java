package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.MensajeDTO;
import co.edu.udes.backend.mappers.MensajeMapper;
import co.edu.udes.backend.models.Mensaje;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MensajeMapper mensajeMapper;

    public MensajeDTO enviarMensaje(MensajeDTO mensajeDTO) {
        Mensaje mensaje = mensajeMapper.toEntity(mensajeDTO);
        
        if (mensajeDTO.getRemitenteId() != null) {
            mensaje.setRemitente(personaRepository.findById(mensajeDTO.getRemitenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Remitente no encontrado")));
        }
        
        if (mensajeDTO.getDestinatarioId() != null) {
            mensaje.setDestinatario(personaRepository.findById(mensajeDTO.getDestinatarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Destinatario no encontrado")));
        }

        mensaje.setFechaEnvio(LocalDateTime.now());
        mensaje.setLeido(false);

        return mensajeMapper.toDTO(mensajeRepository.save(mensaje));
    }

    public List<MensajeDTO> obtenerRecibidos(Long usuarioId) {
        return mensajeRepository.findByDestinatarioId(usuarioId).stream()
            .map(mensajeMapper::toDTO)
            .collect(Collectors.toList());
    }

    public List<MensajeDTO> obtenerEnviados(Long usuarioId) {
        return mensajeRepository.findByRemitenteId(usuarioId).stream()
            .map(mensajeMapper::toDTO)
            .collect(Collectors.toList());
    }

    public MensajeDTO marcarComoLeido(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Mensaje no encontrado"));
        mensaje.setLeido(true);
        return mensajeMapper.toDTO(mensajeRepository.save(mensaje));
    }

    public void eliminarMensaje(Long id) {
        if (!mensajeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mensaje no encontrado");
        }
        mensajeRepository.deleteById(id);
    }
}