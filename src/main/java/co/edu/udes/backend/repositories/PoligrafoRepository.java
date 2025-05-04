package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Employee;
import co.edu.udes.backend.models.Poligrafo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PoligrafoRepository extends JpaRepository<Poligrafo, Long> {
    List<Poligrafo> findByEstudianteId(Long estudianteId);
    boolean existsByEstudianteIdAndAsignaturaIdAndPromedioGreaterThanEqual(Long estudianteId, Long asignaturaId, float notaMinima);


}
