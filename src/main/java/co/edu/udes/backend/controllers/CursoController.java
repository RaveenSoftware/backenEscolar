package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.repositories.CursoRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return ResponseEntity.ok(cursos);
    }

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso savedCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(savedCurso);
    }

    // Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no existe con ID: " + id));
        return ResponseEntity.ok(curso);
    }

    // Actualizar curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no existe con ID: " + id));

        curso.setDocente(cursoDetails.getDocente());
        curso.setNombre(cursoDetails.getNombre());
        curso.setGrupo(cursoDetails.getGrupo());
        curso.setAsignatura(cursoDetails.getAsignatura());
        curso.setSemestreAcademico(cursoDetails.getSemestreAcademico());
        curso.setProgramaAcademico(cursoDetails.getProgramaAcademico());
        curso.setMatriculaAcademica(cursoDetails.getMatriculaAcademica());

        Curso updatedCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(updatedCurso);
    }

    // Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no existe con ID: " + id));

        cursoRepository.delete(curso);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
