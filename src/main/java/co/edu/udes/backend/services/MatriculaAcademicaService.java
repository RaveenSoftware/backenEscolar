package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.models.MatriculaAcademica;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.EstudianteRepository;
import co.edu.udes.backend.repositories.MatriculaAcademicaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MatriculaAcademicaService {

    @Autowired
    private MatriculaAcademicaRepository matriculaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Registrar una nueva matrícula
    public MatriculaAcademica registrarMatricula(MatriculaAcademica matricula) {
        Long estudianteId = matricula.getEstudiante().getId();
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + estudianteId));

        matricula.setEstudiante(estudiante);
        return matriculaRepository.save(matricula);
    }

    // Inscribir un curso a una matrícula
    public MatriculaAcademica inscribirCurso(Long matriculaId, Long cursoId) {
        MatriculaAcademica matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada con ID: " + matriculaId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        if (matricula.getCursos().contains(curso)) {
            throw new IllegalStateException("El curso ya está inscrito en esta matrícula.");
        }

        matricula.getCursos().add(curso);
        return matriculaRepository.save(matricula);
    }

    // Eliminar un curso de una matrícula
    public MatriculaAcademica eliminarCursoDeMatricula(Long matriculaId, Long cursoId) {
        MatriculaAcademica matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada con ID: " + matriculaId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        List<Curso> cursos = matricula.getCursos();
        if (!cursos.remove(curso)) {
            throw new IllegalStateException("El curso no estaba inscrito en esta matrícula.");
        }

        return matriculaRepository.save(matricula);
    }

    // Obtener todas las matrículas de un estudiante
    public List<MatriculaAcademica> obtenerMatriculasPorEstudiante(Long estudianteId) {
        return matriculaRepository.findByEstudianteId(estudianteId);
    }

    // Eliminar matrícula completa
    public void eliminarMatricula(Long id) {
        MatriculaAcademica matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada con ID: " + id));
        matriculaRepository.delete(matricula);
    }

    // Obtener matrícula por ID
    public MatriculaAcademica obtenerPorId(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada con ID: " + id));
    }

    // Listar todas las matrículas
    public List<MatriculaAcademica> obtenerTodas() {
        return matriculaRepository.findAll();
    }

    // Actualizar matrícula
    public MatriculaAcademica actualizarMatricula(Long id, MatriculaAcademica detalles) {
        MatriculaAcademica matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada con ID: " + id));

        matricula.setFecha(detalles.getFecha());
        matricula.setEstado(detalles.isEstado());
        matricula.setCodigo(detalles.getCodigo());
        matricula.setCreditosActuales(detalles.getCreditosActuales());
        matricula.setSemestre(detalles.getSemestre());
        matricula.setCursos(detalles.getCursos());

        return matriculaRepository.save(matricula);
    }
}
