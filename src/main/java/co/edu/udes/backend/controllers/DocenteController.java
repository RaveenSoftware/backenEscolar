//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Docente;
import co.edu.udes.backend.repositories.DocenteRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"*"}
)
@RequestMapping({"/api/v1/"})
public class DocenteController {
    @Autowired
    private DocenteRepository docenteRepository;

    public DocenteController() {
    }

    @GetMapping({"/docentes"})
    public List<Docente> getAllDocentes() {
        return this.docenteRepository.findAll();
    }

    @PostMapping({"/docentes"})
    public Docente createDocente(@RequestBody Docente docente) {
        return (Docente)this.docenteRepository.save(docente);
    }

    @GetMapping({"/docentes/{id}"})
    public ResponseEntity<Docente> getDocenteById(@PathVariable Long id) {
        Docente docente = (Docente)this.docenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Docente not exist with id :" + id));
        return ResponseEntity.ok(docente);
    }

    @PutMapping({"/docentes/{id}"})
    public ResponseEntity<Docente> updateDocente(@PathVariable Long id, @RequestBody Docente docenteDetails) {
        Docente docente = (Docente)this.docenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Docente not exist with id :" + id));
        docente.setNombre(docenteDetails.getNombre());
        docente.setTelefono(docenteDetails.getTelefono());
        docente.setCorreoPersonal(docenteDetails.getCorreoPersonal());
        docente.setFechaNacimiento(docenteDetails.getFechaNacimiento());
        docente.setNumeroDocumento(docenteDetails.getNumeroDocumento());
        docente.setEstado(docenteDetails.isEstado());
        docente.setTipoDocumento(docenteDetails.getTipoDocumento());
        docente.setGenero(docenteDetails.getGenero());
        docente.setFacultad(docenteDetails.getFacultad());
        docente.setEspecialidad(docenteDetails.getEspecialidad());
        docente.setCodigoInstitucional(docenteDetails.getCodigoInstitucional());
        docente.setCorreoInstitucional(docenteDetails.getCorreoInstitucional());
        Docente updatedDocente = (Docente)this.docenteRepository.save(docente);
        return ResponseEntity.ok(updatedDocente);
    }

    @DeleteMapping({"/docentes/{id}"})
    public ResponseEntity<Map<String, Boolean>> deleteDocente(@PathVariable Long id) {
        Docente docente = (Docente)this.docenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Docente not exist with id :" + id));
        this.docenteRepository.delete(docente);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
