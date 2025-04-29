package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.AulaHorario;
import co.edu.udes.backend.models.Calificaciones;
import co.edu.udes.backend.services.HistorialAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/consultas")
@CrossOrigin(origins = "*")
public class ConsultaAcademicaController {

    @Autowired
    private HistorialAcademicoService historialService;

    // 1. Ver el horario de un estudiante
    @GetMapping("/horario/{idEstudiante}")
    public ResponseEntity<List<AulaHorario>> getHorarioEstudiante(@PathVariable Long idEstudiante) {
        List<AulaHorario> horario = historialService.obtenerHorarioEstudiante(idEstudiante);
        return ResponseEntity.ok(horario);
    }

    // 2. Ver notas puestas por un docente
    @GetMapping("/notas-docente/{idDocente}")
    public ResponseEntity<List<Calificaciones>> getNotasPorDocente(@PathVariable Long idDocente) {
        List<Calificaciones> notas = historialService.obtenerNotasPorDocente(idDocente);
        return ResponseEntity.ok(notas);
    }

    // 3. Ver nota final de un estudiante en un curso
    @GetMapping("/nota-final/{idEstudiante}/{idCurso}")
    public ResponseEntity<Map<String, Object>> getNotaFinalCurso(
            @PathVariable Long idEstudiante,
            @PathVariable Long idCurso) {
        Optional<Double> nota = historialService.obtenerNotaFinalCurso(idEstudiante, idCurso);
        Map<String, Object> response = new HashMap<>();
        if (nota.isPresent()) {
            response.put("notaFinal", nota.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", "No se encontró la nota.");
            return ResponseEntity.status(404).body(response);
        }
    }

    // 4. Ver todas las notas finales de un estudiante
    @GetMapping("/notas-finales/{idEstudiante}")
    public ResponseEntity<List<Double>> getNotasFinales(@PathVariable Long idEstudiante) {
        List<Double> notas = historialService.obtenerNotasFinalesEstudiante(idEstudiante);
        return ResponseEntity.ok(notas);
    }
}
