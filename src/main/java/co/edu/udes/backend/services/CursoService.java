package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.models.ProgramaAcademico;
import co.edu.udes.backend.models.Horario;
import co.edu.udes.backend.repositories.AsignaturaRepository;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.HorarioRepository;
import co.edu.udes.backend.repositories.ProgramaAcademicoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProgramaAcademicoRepository programaRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    public Curso crearCurso(Curso curso) {
        Long programaId = curso.getPrograma().getId();
        Long asignaturaId = curso.getAsignatura().getId();
        Long horarioId = curso.getHorario().getId();

        ProgramaAcademico programa = programaRepository.findById(programaId)
                .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado con ID: " + programaId));
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada con ID: " + asignaturaId));
        Horario horario = horarioRepository.findById(horarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Horario no encontrado con ID: " + horarioId));

        curso.setPrograma(programa);
        curso.setAsignatura(asignatura);
        curso.setHorario(horario);

        return cursoRepository.save(curso);
    }

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
    }

    public Curso actualizarCurso(Long id, Curso detalles) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));

        curso.setNombre(detalles.getNombre());
        curso.setCodigo(detalles.getCodigo());
        curso.setCreditos(detalles.getCreditos());
        curso.setContenido(detalles.getContenido());
        curso.setObjetivos(detalles.getObjetivos());
        curso.setCompetencias(detalles.getCompetencias());

        // Reasociar entidades si se envían nuevas referencias
        if (detalles.getPrograma() != null) {
            ProgramaAcademico programa = programaRepository.findById(detalles.getPrograma().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado con ID: " + detalles.getPrograma().getId()));
            curso.setPrograma(programa);
        }
        if (detalles.getAsignatura() != null) {
            Asignatura asignatura = asignaturaRepository.findById(detalles.getAsignatura().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada con ID: " + detalles.getAsignatura().getId()));
            curso.setAsignatura(asignatura);
        }
        if (detalles.getHorario() != null) {
            Horario horario = horarioRepository.findById(detalles.getHorario().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Horario no encontrado con ID: " + detalles.getHorario().getId()));
            curso.setHorario(horario);
        }

        return cursoRepository.save(curso);
    }

    public void eliminarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
        cursoRepository.delete(curso);
    }
}
