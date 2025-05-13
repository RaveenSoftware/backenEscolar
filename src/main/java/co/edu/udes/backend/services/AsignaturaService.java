package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AsignaturaDTO;
import co.edu.udes.backend.mappers.AsignaturaMapper;
import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.repositories.AsignaturaRepository;
import co.edu.udes.backend.repositories.PensumRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private PensumRepository pensumRepository;

    @Autowired
    private AsignaturaMapper asignaturaMapper;

    public AsignaturaDTO crearAsignatura(AsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = asignaturaMapper.toEntity(asignaturaDTO);
        
        if (asignaturaDTO.getPensumId() != null) {
            asignatura.setPensum(pensumRepository.findById(asignaturaDTO.getPensumId())
                .orElseThrow(() -> new ResourceNotFoundException("Pensum no encontrado")));
        }
        
        if (asignaturaDTO.getPredecesoraId() != null) {
            asignatura.setPredecesora(asignaturaRepository.findById(asignaturaDTO.getPredecesoraId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura predecesora no encontrada")));
        }

        return asignaturaMapper.toDTO(asignaturaRepository.save(asignatura));
    }

    public List<AsignaturaDTO> obtenerTodas() {
        return asignaturaRepository.findAll().stream()
            .map(asignaturaMapper::toDTO)
            .collect(Collectors.toList());
    }

    public AsignaturaDTO obtenerPorId(Long id) {
        return asignaturaMapper.toDTO(asignaturaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada")));
    }

    public AsignaturaDTO actualizarAsignatura(Long id, AsignaturaDTO asignaturaDTO) {
        Asignatura existente = asignaturaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada"));

        Asignatura actualizada = asignaturaMapper.toEntity(asignaturaDTO);
        actualizada.setId(existente.getId());
        
        if (asignaturaDTO.getPensumId() != null) {
            actualizada.setPensum(pensumRepository.findById(asignaturaDTO.getPensumId())
                .orElseThrow(() -> new ResourceNotFoundException("Pensum no encontrado")));
        }

        if (asignaturaDTO.getPredecesoraId() != null) {
            actualizada.setPredecesora(asignaturaRepository.findById(asignaturaDTO.getPredecesoraId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura predecesora no encontrada")));
        }

        return asignaturaMapper.toDTO(asignaturaRepository.save(actualizada));
    }

    public void eliminarAsignatura(Long id) {
        if (!asignaturaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asignatura no encontrada");
        }
        asignaturaRepository.deleteById(id);
    }
}