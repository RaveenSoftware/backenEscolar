package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.models.TokenRecuperacion;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.repositories.TokenRecuperacionRepository;
import co.edu.udes.backend.services.TokenRecuperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/token-recuperacion")
@CrossOrigin(origins = "*")
public class TokenRecuperacionController {

    @Autowired
    private TokenRecuperacionService tokenRecuperacionService;

    @Autowired
    private TokenRecuperacionRepository tokenRecuperacionRepository;

    @Autowired
    private PersonaRepository personaRepository;


    // POST: Crear nuevo token
    @PostMapping
    public ResponseEntity<TokenRecuperacion> crear(@RequestBody TokenRecuperacion token) {
        TokenRecuperacion creado = tokenRecuperacionService.crear(token);
        return ResponseEntity.ok(creado);
    }

    // GET: Obtener todos los tokens
    @GetMapping
    public ResponseEntity<List<TokenRecuperacion>> obtenerTodos() {
        return ResponseEntity.ok(tokenRecuperacionService.obtenerTodos());
    }

    // GET: Obtener un token por ID
    @GetMapping("/{id}")
    public ResponseEntity<TokenRecuperacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tokenRecuperacionService.obtenerPorId(id));
    }

    // PUT: Actualizar un token
    @PutMapping("/{id}")
    public ResponseEntity<TokenRecuperacion> actualizar(@PathVariable Long id, @RequestBody TokenRecuperacion nuevo) {
        return ResponseEntity.ok(tokenRecuperacionService.actualizar(id, nuevo));
    }

    // DELETE: Eliminar un token
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        tokenRecuperacionService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/validar-y-cambiar")
    public ResponseEntity<?> validarTokenYCambiarPassword(@RequestBody Map<String, String> datos) {
        String token = datos.get("token");
        String nuevaPassword = datos.get("nuevaPassword");

        // Buscar token en BD
        TokenRecuperacion registro = tokenRecuperacionRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido o no encontrado"));

        // Verificar expiración (ejemplo: 15 minutos)
        LocalDateTime expiracion = registro.getFechaCreacion().plusMinutes(15);
        if (LocalDateTime.now().isAfter(expiracion)) {
            return ResponseEntity.badRequest().body(Map.of("error", "El token ha expirado"));
        }

        // Actualizar "contraseña" (en este caso el númeroDocumento como clave simple)
        Persona persona = registro.getPersona();
        persona.setNumeroDocumento(nuevaPassword); // 👈 REEMPLAZAR esto si implementas password cifrada
        personaRepository.save(persona);

        // Eliminar el token usado
        tokenRecuperacionRepository.delete(registro);

        return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada correctamente"));
    }
}
