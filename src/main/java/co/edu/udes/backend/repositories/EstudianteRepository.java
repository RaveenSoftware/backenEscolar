package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Método personalizado para validar si ya existe un número de documento
    Optional<Estudiante> findByNumeroDocumento(String numeroDocumento);
}
