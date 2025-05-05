package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Horario;
import co.edu.udes.backend.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario crearHorario(Horario horario) {
        if (!esHorarioDisponible(horario)) {
            throw new IllegalArgumentException("Ya existe un horario para el aula que se traslapa con este.");
        }
        return horarioRepository.save(horario);
    }

    public Horario actualizarHorario(Long id, Horario horarioActualizado) {
        Horario existente = horarioRepository.findById(id).orElse(null);
        if (existente == null) {
            throw new IllegalArgumentException("No se encontró el horario con ID: " + id);
        }

        List<Horario> existentes = horarioRepository.findByAula_IdAndDia(
                horarioActualizado.getAula().getId(),
                horarioActualizado.getDia()
        );

        for (Horario otro : existentes) {
            if (otro.getId() != id &&
                    traslapan(
                            horarioActualizado.getHoraInicio(), horarioActualizado.getHoraFinalizacion(),
                            otro.getHoraInicio(), otro.getHoraFinalizacion()
                    )) {
                throw new IllegalArgumentException("El horario se traslapa con otro existente en el aula.");
            }
        }

        existente.setHoraInicio(horarioActualizado.getHoraInicio());
        existente.setHoraFinalizacion(horarioActualizado.getHoraFinalizacion());
        existente.setDia(horarioActualizado.getDia());
        existente.setTipoActividad(horarioActualizado.getTipoActividad());
        existente.setAula(horarioActualizado.getAula());
        existente.setEstado(horarioActualizado.isEstado());

        return horarioRepository.save(existente);
    }

    public boolean esHorarioDisponible(Horario nuevoHorario) {
        List<Horario> existentes = horarioRepository.findByAula_IdAndDia(
                nuevoHorario.getAula().getId(),
                nuevoHorario.getDia()
        );

        for (Horario existente : existentes) {
            if (traslapan(
                    nuevoHorario.getHoraInicio(), nuevoHorario.getHoraFinalizacion(),
                    existente.getHoraInicio(), existente.getHoraFinalizacion()
            )) {
                return false;
            }
        }
        return true;
    }

    private boolean traslapan(LocalTime inicio1, LocalTime fin1, LocalTime inicio2, LocalTime fin2) {
        return inicio1.isBefore(fin2) && inicio2.isBefore(fin1);
    }

    public List<Horario> obtenerTodos() {
        return horarioRepository.findAll();
    }

    public Horario obtenerPorId(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public void eliminarHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
