package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.repositories.AsignaturaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // Listar todas las asignaturas
    public List<Asignatura> listarTodas() {
        return asignaturaRepository.findAll();
    }

    // Buscar por ID
    public Asignatura buscarPorId(Long id) {
        return asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no existe con id: " + id));
    }

    // Crear asignatura
    public Asignatura crear(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    // Actualizar asignatura
    public Asignatura actualizar(Long id, Asignatura detalles) {
        Asignatura asignatura = buscarPorId(id);

        asignatura.setNombre(detalles.getNombre());
        asignatura.setCodigo(detalles.getCodigo());
        asignatura.setNumeroSemestre(detalles.getNumeroSemestre());
        asignatura.setNumeroCreditos(detalles.getNumeroCreditos());
        asignatura.setPredecesora(detalles.getPredecesora());
        asignatura.setPensum(detalles.getPensum());

        return asignaturaRepository.save(asignatura);
    }

    // Eliminar asignatura
    public void eliminar(Long id) {
        Asignatura asignatura = buscarPorId(id);
        asignaturaRepository.delete(asignatura);
    }
}
