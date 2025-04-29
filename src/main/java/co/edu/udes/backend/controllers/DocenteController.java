package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.repositories.DocenteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/docentes")
@CrossOrigin(origins = "*")
public class DocenteController {

    private final DocenteRepository docenteRepository;

    @Autowired
    public DocenteController(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    // Obtener todos los docentes
    @GetMapping
    public ResponseEntity<List<Docente>> getAllDocentes() {
        List<Docente> docentes = docenteRepository.findAll();
        return ResponseEntity.ok(docentes);
    }

    // Crear un nuevo docente
    @PostMapping
    public ResponseEntity<Docente> createDocente(@RequestBody Docente docente) {
        Docente savedDocente = docenteRepository.save(docente);
        return ResponseEntity.ok(savedDocente);
    }

    // Obtener docente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no existe con ID: " + id));
        return ResponseEntity.ok(docente);
    }

    // Actualizar un docente existente
    @PutMapping("/{id}")
    public ResponseEntity<Docente> updateDocente(@PathVariable Long id, @RequestBody Docente docenteDetails) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no existe con ID: " + id));

        docente.setNombre(docenteDetails.getNombre());
        docente.setTelefono(docenteDetails.getTelefono());
        docente.setCorreoPersonal(docenteDetails.getCorreoPersonal());
        docente.setFechaNacimiento(docenteDetails.getFechaNacimiento());
        docente.setNumeroDocumento(docenteDetails.getNumeroDocumento());
        docente.setEstado(docenteDetails.isEstado());
        docente.setTipoDocumento(docenteDetails.getTipoDocumento());
        docente.setGenero(docenteDetails.getGenero());
        docente.setFacultad(docenteDetails.getFacultad());
        docente.setEspecialidad(docenteDetails.getEspecialidad());
        docente.setCodigoInstitucional(docenteDetails.getCodigoInstitucional());
        docente.setCorreoInstitucional(docenteDetails.getCorreoInstitucional());

        Docente updatedDocente = docenteRepository.save(docente);
        return ResponseEntity.ok(updatedDocente);
    }

    // Eliminar docente
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDocente(@PathVariable Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no existe con ID: " + id));

        docenteRepository.delete(docente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
