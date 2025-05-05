package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // Login - devuelve el token si el númeroDocumento y contraseña coinciden
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String documento = loginRequest.get("numeroDocumento");
        String password = loginRequest.get("password");

        // Validar en la base de datos (esto depende de cómo almacenas el password, por ahora simple)
        Persona persona = personaRepository.findByNumeroDocumento(documento)
                .orElseThrow(() -> new RuntimeException("Documento no registrado"));

        if (!persona.getNumeroDocumento().equals(password)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Contraseña incorrecta"));
        }

        // Autenticar
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(documento, password)
        );

        // Generar token
        UserDetails userDetails = userDetailsService.loadUserByUsername(documento);
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", documento,
                "rol", persona.getRol().getNombre()
        ));
    }
}
