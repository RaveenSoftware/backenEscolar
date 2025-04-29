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

    // Listar todos los docentes
    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    // Buscar por ID
    public Docente buscarPorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no existe con id: " + id));
    }

    // Crear nuevo docente
    public Docente crear(Docente docente) {
        return docenteRepository.save(docente);
    }

    // Actualizar docente (solo con atributos existentes)
    public Docente actualizar(Long id, Docente detalles) {
        Docente docente = buscarPorId(id);

        docente.setFacultad(detalles.getFacultad());
        docente.setEspecialidad(detalles.getEspecialidad());
        docente.setCodigoInstitucional(detalles.getCodigoInstitucional());
        docente.setCorreoInstitucional(detalles.getCorreoInstitucional());

        return docenteRepository.save(docente);
    }

    // Eliminar
    public void eliminar(Long id) {
        Docente docente = buscarPorId(id);
        docenteRepository.delete(docente);
    }
}
