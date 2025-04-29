// src/main/java/co/edu/udes/backend/repositories/AsistenciaRepository.java

package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

}
