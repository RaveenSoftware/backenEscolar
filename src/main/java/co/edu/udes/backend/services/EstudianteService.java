package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no existe con id: " + id));
    }

    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante actualizar(Long id, Estudiante detalles) {
        Estudiante estudiante = buscarPorId(id);
        estudiante.setPrograma(detalles.getPrograma());
        estudiante.setCodigoInstitucional(detalles.getCodigoInstitucional());
        estudiante.setCorreoInstitucional(detalles.getCorreoInstitucional());
        return estudianteRepository.save(estudiante);
    }

    public void eliminar(Long id) {
        Estudiante estudiante = buscarPorId(id);
        estudianteRepository.delete(estudiante);
    }
}
