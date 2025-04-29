package co.edu.udes.backend.services;

import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.CalificacionesRepository;
import co.edu.udes.backend.repositories.MatriculaAcademicaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaAcademicaService {

    @Autowired
    private MatriculaAcademicaRepository matriculaAcademicaRepository;

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    public List<MatriculaAcademica> listarTodas() {
        return matriculaAcademicaRepository.findAll();
    }

    public MatriculaAcademica buscarPorId(Long id) {
        return matriculaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no existe con id: " + id));
    }

    public MatriculaAcademica crear(MatriculaAcademica matricula) {
        // Validar prerrequisitos
        for (Curso curso : matricula.getCursos()) {
            Asignatura asignaturaActual = curso.getAsignatura();
            Asignatura predecesora = asignaturaActual.getPredecesora();

            if (predecesora != null) {
                // Validar si el estudiante ha aprobado la asignatura predecesora
                boolean aprobada = calificacionesRepository
                        .findByEstudianteIdAndAsignaturaId(matricula.getEstudiante().getId(), predecesora.getId())
                        .stream()
                        .anyMatch(calificacion -> calificacion.getDefinitiva() >= 3.0);

                if (!aprobada) {
                    throw new IllegalStateException("El estudiante no ha aprobado la asignatura prerequisito: " +
                            predecesora.getNombre());
                }
            }
        }

        return matriculaAcademicaRepository.save(matricula);
    }

    public MatriculaAcademica actualizar(Long id, MatriculaAcademica detalles) {
        MatriculaAcademica matricula = buscarPorId(id);

        matricula.setCodigo(detalles.getCodigo());
        matricula.setFecha(detalles.getFecha());
        matricula.setEstado(detalles.isEstado());
        matricula.setEstudiante(detalles.getEstudiante());
        matricula.setCursos(detalles.getCursos());
        matricula.setSemestre(detalles.getSemestre());
        matricula.setCreditosActuales(detalles.getCreditosActuales());

        return matriculaAcademicaRepository.save(matricula);
    }

    public void eliminar(Long id) {
        MatriculaAcademica matricula = buscarPorId(id);
        matriculaAcademicaRepository.delete(matricula);
    }
}

