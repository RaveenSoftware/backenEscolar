// Service: DocenteCursoService
package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.DocenteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DocenteCursoService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Asignar un curso a un docente
    public Docente asignarCurso(Long docenteId, Long cursoId) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + docenteId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        Set<Curso> cursosActuales = docente.getCursos();
        if (cursosActuales == null) {
            cursosActuales = new HashSet<>();
        }
        cursosActuales.add(curso);
        docente.setCursos(cursosActuales);
        return docenteRepository.save(docente);
    }

    // Obtener cursos asignados a un docente
    public Set<Curso> obtenerCursosPorDocente(Long docenteId) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + docenteId));
        return docente.getCursos();
    }

    // Eliminar un curso asignado a un docente
    public Docente eliminarCurso(Long docenteId, Long cursoId) {
        Docente docente = docenteRepository.findById(docenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con ID: " + docenteId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        docente.getCursos().remove(curso);
        return docenteRepository.save(docente);
    }
}
