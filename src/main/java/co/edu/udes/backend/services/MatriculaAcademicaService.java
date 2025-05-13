package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.MatriculaAcademicaDTO;
import co.edu.udes.backend.mappers.MatriculaAcademicaMapper;
import co.edu.udes.backend.models.MatriculaAcademica;
import co.edu.udes.backend.models.Estudiante;
import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaAcademicaService {

    private static final int MAX_CREDITOS = 20;

    @Autowired
    private MatriculaAcademicaRepository matriculaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaAcademicaMapper matriculaMapper;

    public MatriculaAcademicaDTO registrarMatricula(MatriculaAcademicaDTO matriculaDTO) {
        MatriculaAcademica matricula = matriculaMapper.toEntity(matriculaDTO);
        
        if (matriculaDTO.getEstudianteId() != null) {
            Estudiante estudiante = estudianteRepository.findById(matriculaDTO.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));
            matricula.setEstudiante(estudiante);
        }

        return matriculaMapper.toDTO(matriculaRepository.save(matricula));
    }

    public List<MatriculaAcademicaDTO> obtenerTodas() {
        return matriculaRepository.findAll().stream()
            .map(matriculaMapper::toDTO)
            .collect(Collectors.toList());
    }

    public MatriculaAcademicaDTO obtenerPorId(Long id) {
        return matriculaMapper.toDTO(matriculaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada")));
    }

    public MatriculaAcademicaDTO actualizarMatricula(Long id, MatriculaAcademicaDTO matriculaDTO) {
        MatriculaAcademica existente = matriculaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada"));

        MatriculaAcademica actualizada = matriculaMapper.toEntity(matriculaDTO);
        actualizada.setId(existente.getId());
        
        if (matriculaDTO.getEstudianteId() != null) {
            Estudiante estudiante = estudianteRepository.findById(matriculaDTO.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));
            actualizada.setEstudiante(estudiante);
        }

        return matriculaMapper.toDTO(matriculaRepository.save(actualizada));
    }

    public void eliminarMatricula(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Matrícula no encontrada");
        }
        matriculaRepository.deleteById(id);
    }

    public MatriculaAcademicaDTO inscribirCurso(Long matriculaId, Long cursoId) {
        MatriculaAcademica matricula = matriculaRepository.findById(matriculaId)
            .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada"));
            
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        if (matricula.getCursos().contains(curso)) {
            throw new IllegalStateException("El curso ya está inscrito en esta matrícula");
        }

        int nuevosCreditos = matricula.getCreditosActuales() + curso.getCreditos();
        if (nuevosCreditos > MAX_CREDITOS) {
            throw new IllegalStateException("Excede el máximo de créditos permitidos");
        }

        matricula.getCursos().add(curso);
        matricula.setCreditosActuales(nuevosCreditos);

        return matriculaMapper.toDTO(matriculaRepository.save(matricula));
    }

    public MatriculaAcademicaDTO eliminarCursoDeMatricula(Long matriculaId, Long cursoId) {
        MatriculaAcademica matricula = matriculaRepository.findById(matriculaId)
            .orElseThrow(() -> new ResourceNotFoundException("Matrícula no encontrada"));
            
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        if (!matricula.getCursos().remove(curso)) {
            throw new IllegalStateException("El curso no estaba inscrito en esta matrícula");
        }

        matricula.setCreditosActuales(matricula.getCreditosActuales() - curso.getCreditos());

        return matriculaMapper.toDTO(matriculaRepository.save(matricula));
    }
}