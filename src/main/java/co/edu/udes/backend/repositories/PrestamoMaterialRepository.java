package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.PrestamoMaterial;
import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoMaterialRepository extends JpaRepository<PrestamoMaterial, Long> {

    // Listar todos los préstamos de una persona
    List<PrestamoMaterial> findByPersona(Persona persona);

    // Listar préstamos no devueltos
    List<PrestamoMaterial> findByDevueltoFalse();

    // Buscar por material y estado de devolución
    List<PrestamoMaterial> findByMaterialAndDevuelto(Material material, boolean devuelto);
}
