package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return ResponseEntity.ok(personas);
    }

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona savedPersona = personaRepository.save(persona);
        return ResponseEntity.ok(savedPersona);
    }

    // Obtener persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no existe con ID: " + id));
        return ResponseEntity.ok(persona);
    }

    // Actualizar persona existente
    @PutMapping("/{id}")
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

    // Eliminar persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePersona(@PathVariable Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no existe con ID: " + id));

        personaRepository.delete(persona);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
