package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.MatriculaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaAcademicaRepository extends JpaRepository<MatriculaAcademica, Long> {


}
