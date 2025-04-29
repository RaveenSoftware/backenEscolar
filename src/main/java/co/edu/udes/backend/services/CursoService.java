package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Listar todos los cursos
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    // Buscar curso por ID
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no existe con id: " + id));
    }

    // Crear nuevo curso
    public Curso crear(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Actualizar curso acorde a los campos disponibles
    public Curso actualizar(Long id, Curso detalles) {
        Curso curso = buscarPorId(id);

        curso.setNombre(detalles.getNombre());
        curso.setGrupo(detalles.getGrupo());
        curso.setAsignatura(detalles.getAsignatura());
        curso.setDocente(detalles.getDocente());
        curso.setSemestreAcademico(detalles.getSemestreAcademico());
        curso.setProgramaAcademico(detalles.getProgramaAcademico());
        curso.setMatriculaAcademica(detalles.getMatriculaAcademica());

        return cursoRepository.save(curso);
    }

    // Eliminar curso
    public void eliminar(Long id) {
        Curso curso = buscarPorId(id);
        cursoRepository.delete(curso);
    }
}
