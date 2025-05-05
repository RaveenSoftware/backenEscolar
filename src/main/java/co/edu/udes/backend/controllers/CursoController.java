package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return ResponseEntity.ok(cursos);
    }

    // Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
        return ResponseEntity.ok(curso);
    }

    // Crear nuevo curso
    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        if (curso.getInscritosActuales() == 0) {
            curso.setInscritosActuales(0); // Inicializa en cero si no está definido
        }
        Curso nuevo = cursoRepository.save(curso);
        return ResponseEntity.ok(nuevo);
    }

    // Actualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso detalles) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));

        curso.setNombre(detalles.getNombre());
        curso.setCodigo(detalles.getCodigo());
        curso.setCreditos(detalles.getCreditos());
        curso.setContenido(detalles.getContenido());
        curso.setObjetivos(detalles.getObjetivos());
        curso.setCompetencias(detalles.getCompetencias());
        curso.setPrograma(detalles.getPrograma());
        curso.setAsignatura(detalles.getAsignatura());
        curso.setHorario(detalles.getHorario());
        curso.setDocentes(detalles.getDocentes());
        curso.setCupoMaximo(detalles.getCupoMaximo());
        curso.setPrerrequisitos(detalles.getPrerrequisitos());

        Curso actualizado = cursoRepository.save(curso);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));

        cursoRepository.delete(curso);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}