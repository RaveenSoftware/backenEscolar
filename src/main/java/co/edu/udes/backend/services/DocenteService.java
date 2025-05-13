```java
package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.DocenteDTO;
import co.edu.udes.backend.mappers.DocenteMapper;
import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private TipoGeneroRepository tipoGeneroRepository;

    @Autowired
    private DocenteMapper docenteMapper;

    public DocenteDTO crearDocente(DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toEntity(docenteDTO);
        
        if (docenteDTO.getFacultadId() != null) {
            docente.setFacultad(facultadRepository.findById(docenteDTO.getFacultadId())
                .orElseThrow(() -> new ResourceNotFoundException("Facultad no encontrada")));
        }
        
        if (docenteDTO.getTipoDocumentoId() != null) {
            docente.setTipoDocumento(tipoDocumentoRepository.findById(docenteDTO.getTipoDocumentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado")));
        }
        
        if (docenteDTO.getTipoGeneroId() != null) {
            docente.setGenero(tipoGeneroRepository.findById(docenteDTO.getTipoGeneroId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de género no encontrado")));
        }

        return docenteMapper.toDTO(docenteRepository.save(docente));
    }

    public List<DocenteDTO> obtenerTodos() {
        return docenteRepository.findAll().stream()
            .map(docenteMapper::toDTO)
            .collect(Collectors.toList());
    }

    public DocenteDTO obtenerPorId(Long id) {
        return docenteMapper.toDTO(docenteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado")));
    }

    public DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO) {
        Docente existente = docenteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado"));

        Docente actualizado = docenteMapper.toEntity(docenteDTO);
        actualizado.setId(existente.getId());
        
        if (docenteDTO.getFacultadId() != null) {
            actualizado.setFacultad(facultadRepository.findById(docenteDTO.getFacultadId())
                .orElseThrow(() -> new ResourceNotFoundException("Facultad no encontrada")));
        }

        if (docenteDTO.getTipoDocumentoId() != null) {
            actualizado.setTipoDocumento(tipoDocumentoRepository.findById(docenteDTO.getTipoDocumentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado")));
        }

        if (docenteDTO.getTipoGeneroId() != null) {
            actualizado.setGenero(tipoGeneroRepository.findById(docenteDTO.getTipoGeneroId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de género no encontrado")));
        }

        return docenteMapper.toDTO(docenteRepository.save(actualizado));
    }

    public void eliminarDocente(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Docente no encontrado");
        }
        docenteRepository.deleteById(id);
    }
}
```