package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Buscar cursos por programa académico
    List<Curso> findByProgramaId(Long programaId);

    // Buscar cursos por asignatura
    List<Curso> findByAsignaturaId(Long asignaturaId);

    // Verificar si existe un curso con un código específico
    boolean existsByCodigo(String codigo);
}
