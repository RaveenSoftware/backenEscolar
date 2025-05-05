package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Calificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionesRepository extends JpaRepository<Calificaciones, Long> {

    //  Encuentra todas las calificaciones por ID de docente (relación ManyToMany)
    List<Calificaciones> findByCurso_Docentes_Id(Long docenteId);

    //  Encuentra calificación específica por estudiante y curso
    Optional<Calificaciones> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);

    //  Todas las calificaciones de un estudiante
    List<Calificaciones> findByEstudianteId(Long estudianteId);


}
