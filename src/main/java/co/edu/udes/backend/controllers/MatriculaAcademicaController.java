package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.MatriculaAcademica;
import co.edu.udes.backend.repositories.MatriculaAcademicaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaAcademicaController {

    @Autowired
    private MatriculaAcademicaRepository matriculaRepository;

    // Crear nueva matrícula
    @PostMapping
    public ResponseEntity<MatriculaAcademica> crearMatricula(@RequestBody MatriculaAcademica matricula) {
        MatriculaAcademica nueva = matriculaRepository.save(matricula);
        return ResponseEntity.ok(nueva);
    }

    // Listar todas las matrículas
    @GetMapping
    public ResponseEntity<List<MatriculaAcademica>> listarTodas() {
        List<MatriculaAcademica> lista = matriculaRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    // Obtener una matrícula por ID
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> obtenerPorId(@PathVariable Long id) {
        MatriculaAcademica matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no existe con ID: " + id));
        return ResponseEntity.ok(matricula);
    }

    // Actualizar matrícula
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaAcademica> actualizarMatricula(@PathVariable Long id, @RequestBody MatriculaAcademica detalles) {
        MatriculaAcademica matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no existe con ID: " + id));

        matricula.setCodigo(detalles.getCodigo());
        matricula.setFecha(detalles.getFecha());
        matricula.setEstado(detalles.isEstado());
        matricula.setEstudiante(detalles.getEstudiante());
        matricula.setCursos(detalles.getCursos());
        matricula.setSemestre(detalles.getSemestre());
        matricula.setCreditosActuales(detalles.getCreditosActuales());

        MatriculaAcademica actualizada = matriculaRepository.save(matricula);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar matrícula
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id) {
        MatriculaAcademica matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula no existe con ID: " + id));

        matriculaRepository.delete(matricula);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
