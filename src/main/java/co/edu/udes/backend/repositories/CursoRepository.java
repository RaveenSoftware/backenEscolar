package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Buscar cursos asignados a un docente específico
    List<Curso> findByDocenteId(Long docenteId);
}
