package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Aula;
import co.edu.udes.backend.models.TipoGenero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {



}
