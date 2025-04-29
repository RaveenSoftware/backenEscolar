// src/main/java/co/edu/udes/backend/repositories/CalificacionesRepository.java

package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Calificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionesRepository extends JpaRepository<Calificaciones, Long> {

}
