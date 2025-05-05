package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.models.TokenRecuperacion;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.repositories.TokenRecuperacionRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenRecuperacionService {

    @Autowired
    private TokenRecuperacionRepository tokenRecuperacionRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private JavaMailSender mailSender;

    // ✅ Crear manualmente un token
    public TokenRecuperacion crear(TokenRecuperacion token) {
        return tokenRecuperacionRepository.save(token);
    }

    // ✅ Obtener todos
    public List<TokenRecuperacion> obtenerTodos() {
        return tokenRecuperacionRepository.findAll();
    }

    // ✅ Obtener por ID
    public TokenRecuperacion obtenerPorId(Long id) {
        return tokenRecuperacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Token no encontrado con ID: " + id));
    }

    // ✅ Actualizar
    public TokenRecuperacion actualizar(Long id, TokenRecuperacion nuevo) {
        TokenRecuperacion existente = obtenerPorId(id);
        existente.setToken(nuevo.getToken());
        existente.setFechaCreacion(nuevo.getFechaCreacion());
        existente.setPersona(nuevo.getPersona());
        return tokenRecuperacionRepository.save(existente);
    }

    // ✅ Eliminar
    public void eliminar(Long id) {
        TokenRecuperacion token = obtenerPorId(id);
        tokenRecuperacionRepository.delete(token);
    }

    // ✅ Lógica: Enviar token de recuperación por correo
    public void enviarTokenRecuperacion(String correo) {
        Persona persona = personaRepository.findByCorreoPersonal(correo)
                .orElseThrow(() -> new ResourceNotFoundException("No existe usuario con ese correo"));

        // Generar token único
        String token = UUID.randomUUID().toString();

        // Guardar token
        TokenRecuperacion nuevoToken = new TokenRecuperacion();
        nuevoToken.setToken(token);
        nuevoToken.setFechaCreacion(LocalDateTime.now());
        nuevoToken.setPersona(persona);
        tokenRecuperacionRepository.save(nuevoToken);

        // Enviar por correo
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            helper.setTo(correo);
            helper.setSubject("Recuperación de contraseña - Socrates");
            helper.setText("""
                <p>Hola, %s:</p>
                <p>Tu código de recuperación es: <b>%s</b></p>
                <p>Este código es válido por 15 minutos.</p>
                """.formatted(persona.getNombre(), token), true);
            mailSender.send(mensaje);
        } catch (MessagingException e) {
            throw new RuntimeException("No se pudo enviar el correo: " + e.getMessage());
        }
    }
}
