package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    // Consultas específicas opcionales
    List<Evaluacion> findByEstudianteId(Long estudianteId);

    List<Evaluacion> findByAsignaturaId(Long asignaturaId);
}