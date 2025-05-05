package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Corrección aquí
    List<Horario> findByAula_IdAndDia(Long aulaId, String dia);
}
