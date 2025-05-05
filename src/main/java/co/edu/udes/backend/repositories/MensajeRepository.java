package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    // Obtener mensajes enviados por una persona
    List<Mensaje> findByRemitenteId(Long remitenteId);

    // Obtener mensajes recibidos por una persona
    List<Mensaje> findByDestinatarioId(Long destinatarioId);





}
