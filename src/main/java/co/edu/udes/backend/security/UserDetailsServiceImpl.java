package co.edu.udes.backend.security;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public UserDetails loadUserByUsername(String numeroDocumento) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new UsernameNotFoundException("Documento no encontrado: " + numeroDocumento));

        String rolNombre = persona.getRol().getNombre();
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + rolNombre.toUpperCase())
        );

        return new User(
                persona.getNumeroDocumento(),   // username
                persona.getNumeroDocumento(),   // password (ajustar si luego encriptas)
                persona.isEstado(),
                true, true, true,
                authorities
        );
    }
}
