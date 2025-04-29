package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Evaluacion;
import co.edu.udes.backend.repositories.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @GetMapping
    public List<Evaluacion> getAll() {
        return evaluacionRepository.findAll();
    }

    @PostMapping
    public Evaluacion create(@RequestBody Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    @GetMapping("/{id}")
    public Evaluacion getById(@PathVariable Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        evaluacionRepository.deleteById(id);
    }
}
