package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByCorreoPersonal(String correoPersonal);

    Optional<Persona> findByNumeroDocumento(String numeroDocumento);



}
