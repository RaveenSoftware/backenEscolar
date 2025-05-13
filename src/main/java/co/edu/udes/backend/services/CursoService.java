```java
package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.CursoDTO;
import co.edu.udes.backend.mappers.CursoMapper;
import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.models.ProgramaAcademico;
import co.edu.udes.backend.models.Horario;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CursoMapper cursoMapper;

    public CursoDTO crearCurso(CursoDTO cursoDTO) {
        Curso curso = cursoMapper.toEntity(cursoDTO);
        
        if (cursoDTO.getProgramaId() != null) {
            curso.setPrograma(programaRepository.findById(cursoDTO.getProgramaId())
                .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado")));
        }
        
        if (cursoDTO.getAsignaturaId() != null) {
            curso.setAsignatura(asignaturaRepository.findById(cursoDTO.getAsignaturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada")));
        }
        
        if (cursoDTO.getHorarioId() != null) {
            curso.setHorario(horarioRepository.findById(cursoDTO.getHorarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Horario no encontrado")));
        }

        return cursoMapper.toDTO(cursoRepository.save(curso));
    }

    public List<CursoDTO> obtenerTodos() {
        return cursoRepository.findAll().stream()
            .map(cursoMapper::toDTO)
            .collect(Collectors.toList());
    }

    public CursoDTO obtenerPorId(Long id) {
        return cursoMapper.toDTO(cursoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado")));
    }

    public CursoDTO actualizarCurso(Long id, CursoDTO cursoDTO) {
        Curso existente = cursoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        Curso actualizado = cursoMapper.toEntity(cursoDTO);
        actualizado.setId(existente.getId());
        
        if (cursoDTO.getProgramaId() != null) {
            actualizado.setPrograma(programaRepository.findById(cursoDTO.getProgramaId())
                .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado")));
        }

        if (cursoDTO.getAsignaturaId() != null) {
            actualizado.setAsignatura(asignaturaRepository.findById(cursoDTO.getAsignaturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada")));
        }

        if (cursoDTO.getHorarioId() != null) {
            actualizado.setHorario(horarioRepository.findById(cursoDTO.getHorarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Horario no encontrado")));
        }

        return cursoMapper.toDTO(cursoRepository.save(actualizado));
    }

    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Curso no encontrado");
        }
        cursoRepository.deleteById(id);
    }
}
```