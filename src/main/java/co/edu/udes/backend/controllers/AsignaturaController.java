package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Asignatura;
import co.edu.udes.backend.repositories.AsignaturaRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost")
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/")
public class AsignaturaController {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // get all asignaturas
    @GetMapping("/asignaturas")
    public List<Asignatura> getAllAsignaturas(){
        return asignaturaRepository.findAll();
    }

    // create asignatura rest api
    @PostMapping("/asignaturas")
    public Asignatura createAsignatura(@RequestBody Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    // get asignatura by id rest api
    @GetMapping("/asignaturas/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura not exist with id :" + id));
        return ResponseEntity.ok(asignatura);
    }

    // update asignatura rest api

    @PutMapping("/asignaturas/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Long id, @RequestBody Asignatura asignaturaDetails){
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura not exist with id :" + id));

        asignatura.setCodigo(asignaturaDetails.getCodigo());
        asignatura.setNombre(asignaturaDetails.getNombre());
        asignatura.setPredecesora(asignaturaDetails.getPredecesora());
        asignatura.setNumeroSemestre(asignaturaDetails.getNumeroSemestre());
        asignatura.setNumeroCreditos(asignaturaDetails.getNumeroCreditos());


        Asignatura updatedAsignatura = asignaturaRepository.save(asignatura);
        return ResponseEntity.ok(updatedAsignatura);
    }

    // delete asignatura rest api
    @DeleteMapping("/asignaturas/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsignatura(@PathVariable Long id){
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura not exist with id :" + id));

        asignaturaRepository.delete(asignatura);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}