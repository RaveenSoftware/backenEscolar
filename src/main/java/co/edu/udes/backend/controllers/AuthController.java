package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;


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

        // Verificar existencia del usuario
        Persona persona = personaRepository.findByNumeroDocumento(documento)
                .orElseThrow(() -> new RuntimeException("Documento no registrado"));

        try {
            // Autenticar con Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(documento, password)
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }

        // Cargar detalles y generar token
        UserDetails userDetails = userDetailsService.loadUserByUsername(documento);
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", documento,
                "rol", persona.getRol().getNombre()
        ));
    }
}