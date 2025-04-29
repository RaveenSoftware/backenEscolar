package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Administrador;
import co.edu.udes.backend.models.AulaHorario;
import co.edu.udes.backend.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AulaHorarioRepository extends JpaRepository<AulaHorario, Long> {


    List<AulaHorario> findByCurso(Curso curso);

}
