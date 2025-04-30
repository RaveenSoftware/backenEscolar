package co.edu.udes.backend.repositories;
import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByMatriculaAcademicaEstudiante(Estudiante estudiante);
}