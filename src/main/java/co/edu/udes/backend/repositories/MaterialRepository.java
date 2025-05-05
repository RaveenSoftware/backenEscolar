package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByDisponible(boolean disponible);
}
