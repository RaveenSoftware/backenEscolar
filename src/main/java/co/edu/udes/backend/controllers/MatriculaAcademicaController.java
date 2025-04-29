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

//@CrossOrigin(origins = "http://localhost")
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/")
public class MatriculaAcademicaController {

    @Autowired
    private MatriculaAcademicaRepository matriculaAcademicaRepository;

    // get all matriculaAcademicas
    @GetMapping("/matriculaAcademicas")
    public List<MatriculaAcademica> getAllMatriculaAcademicas(){
        return matriculaAcademicaRepository.findAll();
    }

    // create matriculaAcademica rest api
    @PostMapping("/matriculaAcademicas")
    public MatriculaAcademica createMatriculaAcademica(@RequestBody MatriculaAcademica matriculaAcademica) {
        return matriculaAcademicaRepository.save(matriculaAcademica);
    }

    // get matriculaAcademica by id rest api
    @GetMapping("/matriculaAcademicas/{id}")
    public ResponseEntity<MatriculaAcademica> getMatriculaAcademicaById(@PathVariable Long id) {
        MatriculaAcademica matriculaAcademica = matriculaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MatriculaAcademica not exist with id :" + id));
        return ResponseEntity.ok(matriculaAcademica);
    }

    // update matriculaAcademica rest api

    @PutMapping("/matriculaAcademicas/{id}")
    public ResponseEntity<MatriculaAcademica> updateMatriculaAcademica(@PathVariable Long id, @RequestBody MatriculaAcademica matriculaAcademicaDetails){
        MatriculaAcademica matriculaAcademica = matriculaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MatriculaAcademica not exist with id :" + id));

        matriculaAcademica.setCodigo(matriculaAcademicaDetails.getCodigo());
        matriculaAcademica.setFecha(matriculaAcademicaDetails.getFecha());
        matriculaAcademica.setEstado(matriculaAcademicaDetails.isEstado());
        matriculaAcademica.setEstudiante(matriculaAcademicaDetails.getEstudiante());
        matriculaAcademica.setCursos(matriculaAcademicaDetails.getCursos());
        matriculaAcademica.setSemestre(matriculaAcademicaDetails.getSemestre());
        matriculaAcademica.setCreditosActuales(matriculaAcademicaDetails.getCreditosActuales());


        MatriculaAcademica updatedMatriculaAcademica = matriculaAcademicaRepository.save(matriculaAcademica);
        return ResponseEntity.ok(updatedMatriculaAcademica);
    }

    // delete matriculaAcademica rest api
    @DeleteMapping("/matriculaAcademicas/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMatriculaAcademica(@PathVariable Long id){
        MatriculaAcademica matriculaAcademica = matriculaAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MatriculaAcademica not exist with id :" + id));

        matriculaAcademicaRepository.delete(matriculaAcademica);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}