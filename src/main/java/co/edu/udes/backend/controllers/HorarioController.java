package co.edu.udes.backend.controllers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import co.edu.udes.backend.models.Horario;
import co.edu.udes.backend.repositories.HorarioRepository;
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
public class HorarioController {
    @Autowired
    private HorarioRepository horarioRepository;

    public HorarioController() {
    }

    @GetMapping({"/horarios"})
    public List<Horario> getAllHorarios() {
        return this.horarioRepository.findAll();
    }

    @PostMapping({"/horarios"})
    public Horario createHorario(@RequestBody Horario horario) {
        return (Horario)this.horarioRepository.save(horario);
    }

    @GetMapping({"/horarios/{id}"})
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        Horario horario = (Horario)this.horarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Horario not exist with id :" + id));
        return ResponseEntity.ok(horario);
    }

    @PutMapping({"/horarios/{id}"})
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @RequestBody Horario horarioDetails) {
        Horario horario = (Horario)this.horarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Horario not exist with id :" + id));
        horario.setHoraInicio(horarioDetails.getHoraInicio());
        horario.setHoraFinalizacion(horarioDetails.getHoraFinalizacion());
        horario.setDia(horarioDetails.getDia());
        horario.setEstado(horarioDetails.isEstado());
        Horario updatedHorario = (Horario)this.horarioRepository.save(horario);
        return ResponseEntity.ok(updatedHorario);
    }

    @DeleteMapping({"/horarios/{id}"})
    public ResponseEntity<Map<String, Boolean>> deleteHorario(@PathVariable Long id) {
        Horario horario = (Horario)this.horarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Horario not exist with id :" + id));
        this.horarioRepository.delete(horario);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

