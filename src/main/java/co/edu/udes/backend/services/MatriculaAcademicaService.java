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

@Service
public class MatriculaAcademicaService {

    private static final int MAX_CREDITOS = 20;

    @Autowired
    private MatriculaAcademicaRepository matriculaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Registrar nueva matrícula
    public MatriculaAcademica registrarMatricula(MatriculaAcademica matricula) {
        Long estudianteId = matricula.getEstudiante().getId();
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + estudianteId));

        matricula.setEstudiante(estudiante);
        return matriculaRepository.save(matricula);
    }

    // Inscribir curso a una matrícula
    public MatriculaAcademica inscribirCurso(Long matriculaId, Long cursoId) {
        MatriculaAcademica matricula = obtenerPorId(matriculaId);
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        if (matricula.getCursos().contains(curso)) {
            throw new IllegalStateException("El curso ya está inscrito en esta matrícula.");
        }

        int nuevosCreditos = matricula.getCreditosActuales() + curso.getCreditos();
        if (nuevosCreditos > MAX_CREDITOS) {
            throw new IllegalStateException("No se puede inscribir el curso: supera el máximo de créditos permitidos.");
        }

        if (!cumplePrerrequisitos(matricula.getEstudiante(), curso)) {
            throw new IllegalStateException("El estudiante no cumple los prerrequisitos del curso.");
        }

        matricula.getCursos().add(curso);
        matricula.setCreditosActuales(nuevosCreditos);
        return matriculaRepository.save(matricula);
    }

    // Eliminar curso de matrícula
    public MatriculaAcademica eliminarCursoDeMatricula(Long matriculaId, Long cursoId) {
        MatriculaAcademica matricula = obtenerPorId(matriculaId);
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + cursoId));

        if (!matricula.getCursos().remove(curso)) {
            throw new IllegalStateException("El curso no estaba inscrito en esta matrícula.");
        }

        int nuevosCreditos = matricula.getCreditosActuales() - curso.getCreditos();
        matricula.setCreditosActuales(Math.max(nuevosCreditos, 0));

        return matriculaRepository.save(matricula);
    }

    // Obtener matrículas por estudiante
    public List<MatriculaAcademica> obtenerMatriculasPorEstudiante(Long estudianteId) {
        return matriculaRepository.findByEstudianteId(estudianteId);
    }

    // Eliminar matrícula completa
    public void eliminarMatricula(Long id) {
        MatriculaAcademica matricula = obtenerPorId(id);
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
        MatriculaAcademica matricula = obtenerPorId(id);

        matricula.setFecha(detalles.getFecha());
        matricula.setEstado(detalles.isEstado());
        matricula.setCodigo(detalles.getCodigo());
        matricula.setCreditosActuales(detalles.getCreditosActuales());
        matricula.setSemestre(detalles.getSemestre());
        matricula.setCursos(detalles.getCursos());

        return matriculaRepository.save(matricula);
    }

    // Validación (simulada) de prerrequisitos
    private boolean cumplePrerrequisitos(Estudiante estudiante, Curso curso) {
        // Lógica futura: revisar si ya cursó ciertas asignaturas
        return true;
    }
}
