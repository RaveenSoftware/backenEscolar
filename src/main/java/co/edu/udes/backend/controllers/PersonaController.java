package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/personas")
@CrossOrigin(origins = "*")
public class PersonaController {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaController(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // Solo ADMIN puede ver todas las personas
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Persona>> getAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return ResponseEntity.ok(personas);
    }

    // Solo ADMIN puede crear personas
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona savedPersona = personaRepository.save(persona);
        return ResponseEntity.ok(savedPersona);
    }

    // Solo ADMIN puede consultar persona por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no existe con ID: " + id));
        return ResponseEntity.ok(persona);
    }

    // Solo ADMIN puede actualizar
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no existe con ID: " + id));

        persona.setNombre(personaDetails.getNombre());
        persona.setTelefono(personaDetails.getTelefono());
        persona.setCorreoPersonal(personaDetails.getCorreoPersonal());
        persona.setFechaNacimiento(personaDetails.getFechaNacimiento());
        persona.setNumeroDocumento(personaDetails.getNumeroDocumento());
        persona.setEstado(personaDetails.isEstado());

        Persona updatedPersona = personaRepository.save(persona);
        return ResponseEntity.ok(updatedPersona);
    }

    // Solo ADMIN puede eliminar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deletePersona(@PathVariable Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no existe con ID: " + id));

        personaRepository.delete(persona);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
