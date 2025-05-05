package co.edu.udes.backend.security;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public UserDetails loadUserByUsername(String correoPersonal) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByCorreoPersonal(correoPersonal)
                .orElseThrow(() -> new UsernameNotFoundException("Correo no encontrado: " + correoPersonal));

        return new User(
                persona.getCorreoPersonal(),
                persona.getNumeroDocumento(), // se está usando como contraseña por ahora
                persona.isEstado(), // habilitado o no
                true, true, true,
                Collections.emptyList() // aquí irían los roles si los usas como GrantedAuthority
        );
    }
}
