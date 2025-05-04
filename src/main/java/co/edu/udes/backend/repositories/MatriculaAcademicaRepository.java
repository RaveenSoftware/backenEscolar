package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.MatriculaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaAcademicaRepository extends JpaRepository<MatriculaAcademica, Long> {

    List<MatriculaAcademica> findByEstudianteId(Long estudianteId);
}
