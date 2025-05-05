package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.TokenRecuperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRecuperacionRepository extends JpaRepository<TokenRecuperacion, Long> {

    Optional<TokenRecuperacion> findByToken(String token);

    // Si necesitas buscar por correoPersonal (desde persona), usa:
    Optional<TokenRecuperacion> findByPersonaCorreoPersonal(String correoPersonal);

    void deleteByPersonaCorreoPersonal(String correoPersonal);
}
