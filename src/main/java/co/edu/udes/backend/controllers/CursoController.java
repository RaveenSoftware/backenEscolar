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

//@CrossOrigin(origins = "http://localhost")
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // get all cursos
    @GetMapping("/cursos")
    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }

    // create curso rest api
    @PostMapping("/cursos")
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    // get curso by id rest api
    @GetMapping("/cursos/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso not exist with id :" + id));
        return ResponseEntity.ok(curso);
    }

    // update curso rest api

    @PutMapping("/cursos/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails){
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso not exist with id :" + id));

        curso.setDocente(cursoDetails.getDocente());
        curso.setNombre(cursoDetails.getNombre());
        curso.setGrupo(cursoDetails.getGrupo());
        curso.setAsignatura(cursoDetails.getAsignatura());
//        curso.setAulaHorarios(cursoDetails.getAulaHorarios());
        curso.setSemestreAcademico(cursoDetails.getSemestreAcademico());
        curso.setProgramaAcademico(cursoDetails.getProgramaAcademico());
        curso.setMatriculaAcademica(cursoDetails.getMatriculaAcademica());

        Curso updatedCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(updatedCurso);
    }

    // delete curso rest api
    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCurso(@PathVariable Long id){
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso not exist with id :" + id));

        cursoRepository.delete(curso);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}