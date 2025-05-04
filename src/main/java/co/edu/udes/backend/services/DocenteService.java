package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.repositories.DocenteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    // Crear nuevo docente
    public Docente crearDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    // Obtener todos los docentes
    public List<Docente> obtenerTodos() {
        return docenteRepository.findAll();
    }

    // Obtener docente por ID
    public Docente obtenerPorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + id));
    }

    // Actualizar docente
    public Docente actualizarDocente(Long id, Docente docenteDetails) {
        Docente docente = obtenerPorId(id);

        docente.setNombre(docenteDetails.getNombre());
        docente.setCorreoPersonal(docenteDetails.getCorreoPersonal());
        docente.setTelefono(docenteDetails.getTelefono());
        docente.setNumeroDocumento(docenteDetails.getNumeroDocumento());
        docente.setEstado(docenteDetails.isEstado());
        docente.setEspecialidad(docenteDetails.getEspecialidad());

        return docenteRepository.save(docente);
    }

    // Eliminar docente
    public void eliminarDocente(Long id) {
        Docente docente = obtenerPorId(id);
        docenteRepository.delete(docente);
    }
}
