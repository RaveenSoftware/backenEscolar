package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.MatriculaAcademica;
import co.edu.udes.backend.repositories.MatriculaAcademicaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/matriculas-academicas")
@CrossOrigin(origins = "*")
public class MatriculaAcademicaController {

    private final MatriculaAcademicaRepository matriculaAcademicaRepository;

    @Autowired
    public MatriculaAcademicaController(MatriculaAcademicaRepository matriculaAcademicaRepository) {
        this.matriculaAcademicaRepository = matriculaAcademicaRepository;
    }

    // Obtener todas las matrículas académicas
    @GetMapping
    public ResponseEntity<List<MatriculaAcademica>> getAllMatriculaAcademicas() {
        List<MatriculaAcademica> lista = matriculaAcademicaRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    // Crear una nueva matrícula académica
    @PostMapping
    public ResponseEntity<MatriculaAcademica> createMatriculaAcademica(@RequestBody MatriculaAcademica matriculaAcademica) {
        MatriculaAcademica saved = matriculaAcademicaRepository.save(matriculaAcademica);
        return ResponseEntity.ok(saved);
    }

    // Obtener matrícula académica por ID
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> getMatriculaAcademicaById(@PathVariable Long id) {
        MatriculaAcademica matricula = matriculaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La matrícula académica no existe con ID: " + id));
        return ResponseEntity.ok(matricula);
    }

    // Actualizar matrícula académica existente
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> updateMatriculaAcademica(@PathVariable Long id, @RequestBody MatriculaAcademica details) {
        MatriculaAcademica matricula = matriculaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La matrícula académica no existe con ID: " + id));

        matricula.setCodigo(details.getCodigo());
        matricula.setFecha(details.getFecha());
        matricula.setEstado(details.isEstado());
        matricula.setEstudiante(details.getEstudiante());
        matricula.setCursos(details.getCursos());
        matricula.setSemestre(details.getSemestre());
        matricula.setCreditosActuales(details.getCreditosActuales());

        MatriculaAcademica updated = matriculaAcademicaRepository.save(matricula);
        return ResponseEntity.ok(updated);
    }

    // Eliminar matrícula académica
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMatriculaAcademica(@PathVariable Long id) {
        MatriculaAcademica matricula = matriculaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La matrícula académica no existe con ID: " + id));

        matriculaAcademicaRepository.delete(matricula);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
