package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Aula;
import co.edu.udes.backend.models.ReservaEspacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaEspacioRepository extends JpaRepository<ReservaEspacio, Long> {

    //  Buscar reservas por aula y estado (usado para validar disponibilidad)
    List<ReservaEspacio> findByAulaAndEstado(Aula aula, boolean estado);

    //  Buscar reservas en una fecha específica para un aula (útil para reportes o filtros)
    List<ReservaEspacio> findByAulaAndFecha(Aula aula, LocalDate fecha);
}
