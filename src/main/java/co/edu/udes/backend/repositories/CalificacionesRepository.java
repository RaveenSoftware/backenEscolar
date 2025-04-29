package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface CalificacionesRepository extends JpaRepository<Calificaciones, Long> {

    List<Calificaciones> findByCurso_Docente_Id(Long docenteId);

    Optional<Calificaciones> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);

    List<Calificaciones> findByEstudianteId(Long estudianteId);

}
