package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Asistencia;
import co.edu.udes.backend.models.EstadoAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByEstudianteId(Long estudianteId);

    List<Asistencia> findByCursoId(Long cursoId);

    List<Asistencia> findByEstudianteIdAndFecha(Long estudianteId, LocalDate fecha);

    List<Asistencia> findByEstudianteIdAndEstado(Long estudianteId, EstadoAsistencia estado);
}
