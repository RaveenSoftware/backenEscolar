package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {


    @Repository
    public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
        List<Asignatura> findByPensumId(Long pensumId);

}
