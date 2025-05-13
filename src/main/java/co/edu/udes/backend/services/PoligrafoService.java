package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.PoligrafoDTO;
import co.edu.udes.backend.mappers.PoligrafoMapper;
import co.edu.udes.backend.models.Poligrafo;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoligrafoService {

    @Autowired
    private PoligrafoRepository poligrafoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Autowired
    private SemestreAcademicoRepository semestreRepository;

    @Autowired
    private PoligrafoMapper poligrafoMapper;

    public PoligrafoDTO crearPoligrafo(PoligrafoDTO poligrafoDTO) {
        Poligrafo poligrafo = poligrafoMapper.toEntity(poligrafoDTO);
        
        if (poligrafoDTO.getEstudianteId() != null) {
            poligrafo.setEstudiante(estudianteRepository.findById(poligrafoDTO.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado")));
        }
        
        if (poligrafoDTO.getAsignaturaId() != null) {
            poligrafo.setAsignatura(asignaturaRepository.findById(poligrafoDTO.getAsignaturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada")));
        }
        
        if (poligrafoDTO.getCalificacionesId() != null) {
            poligrafo.setCalificaciones(calificacionesRepository.findById(poligrafoDTO.getCalificacionesId())
                .orElseThrow(() -> new ResourceNotFoundException("Calificaciones no encontradas")));
        }
        
        if (poligrafoDTO.getSemestreAcademicoId() != null) {
            poligrafo.setSemestreAcademico(semestreRepository.findById(poligrafoDTO.getSemestreAcademicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Semestre académico no encontrado")));
        }

        return poligrafoMapper.toDTO(poligrafoRepository.save(poligrafo));
    }

    public List<PoligrafoDTO> obtenerTodos() {
        return poligrafoRepository.findAll().stream()
            .map(poligrafoMapper::toDTO)
            .collect(Collectors.toList());
    }

    public PoligrafoDTO obtenerPorId(Long id) {
        return poligrafoMapper.toDTO(poligrafoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no encontrado")));
    }

    public PoligrafoDTO actualizarPoligrafo(Long id, PoligrafoDTO poligrafoDTO) {
        Poligrafo existente = poligrafoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Polígrafo no encontrado"));

        Poligrafo actualizado = poligrafoMapper.toEntity(poligrafoDTO);
        actualizado.setId(existente.getId());
        
        if (poligrafoDTO.getEstudianteId() != null) {
            actualizado.setEstudiante(estudianteRepository.findById(poligrafoDTO.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado")));
        }
        
        if (poligrafoDTO.getAsignaturaId() != null) {
            actualizado.setAsignatura(asignaturaRepository.findById(poligrafoDTO.getAsignaturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada")));
        }
        
        if (poligrafoDTO.getCalificacionesId() != null) {
            actualizado.setCalificaciones(calificacionesRepository.findById(poligrafoDTO.getCalificacionesId())
                .orElseThrow(() -> new ResourceNotFoundException("Calificaciones no encontradas")));
        }
        
        if (poligrafoDTO.getSemestreAcademicoId() != null) {
            actualizado.setSemestreAcademico(semestreRepository.findById(poligrafoDTO.getSemestreAcademicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Semestre académico no encontrado")));
        }

        return poligrafoMapper.toDTO(poligrafoRepository.save(actualizado));
    }

    public void eliminarPoligrafo(Long id) {
        if (!poligrafoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Polígrafo no encontrado");
        }
        poligrafoRepository.deleteById(id);
    }
}