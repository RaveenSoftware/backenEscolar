package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionesRepository extends JpaRepository<Calificaciones, Long> {


}
