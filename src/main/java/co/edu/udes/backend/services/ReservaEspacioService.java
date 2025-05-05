package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Aula;
import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.models.ReservaEspacio;
import co.edu.udes.backend.repositories.AulaRepository;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.repositories.ReservaEspacioRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaEspacioService {

    @Autowired
    private ReservaEspacioRepository reservaEspacioRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    // ✅ Crear una nueva reserva
    public ReservaEspacio crearReserva(ReservaEspacio reserva) {
        Aula aula = aulaRepository.findById(reserva.getAula().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Aula no encontrada"));
        Persona persona = personaRepository.findById(reserva.getSolicitante().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Solicitante no encontrado"));

        reserva.setAula(aula);
        reserva.setSolicitante(persona);

        if (reserva.isEstado()) {
            if (!estaDisponible(aula, reserva.getFecha(), reserva.getHoraInicio(), reserva.getHoraFin())) {
                throw new IllegalArgumentException("Ya existe una reserva aprobada en ese horario para el aula seleccionada.");
            }
        }

        return reservaEspacioRepository.save(reserva);
    }

    // ✅ Validar disponibilidad para evitar traslapes
    public boolean estaDisponible(Aula aula, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        List<ReservaEspacio> reservas = reservaEspacioRepository.findByAulaAndEstado(aula, true);
        return reservas.stream().noneMatch(r ->
                r.getFecha().isEqual(fecha) &&
                        inicio.isBefore(r.getHoraFin()) &&
                        fin.isAfter(r.getHoraInicio())
        );
    }

    // ✅ Obtener todas las reservas
    public List<ReservaEspacio> obtenerTodas() {
        return reservaEspacioRepository.findAll();
    }

    // ✅ Obtener reserva por ID
    public ReservaEspacio obtenerPorId(Long id) {
        return reservaEspacioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
    }

    // ✅ Actualizar una reserva
    public ReservaEspacio actualizarReserva(Long id, ReservaEspacio nueva) {
        ReservaEspacio existente = obtenerPorId(id);

        if (nueva.isEstado() && !estaDisponible(nueva.getAula(), nueva.getFecha(), nueva.getHoraInicio(), nueva.getHoraFin())) {
            throw new IllegalArgumentException("La reserva aprobada se solapa con otra existente.");
        }

        existente.setFecha(nueva.getFecha());
        existente.setHoraInicio(nueva.getHoraInicio());
        existente.setHoraFin(nueva.getHoraFin());
        existente.setTipoActividad(nueva.getTipoActividad());
        existente.setMotivo(nueva.getMotivo());
        existente.setAula(nueva.getAula());
        existente.setSolicitante(nueva.getSolicitante());
        existente.setEstado(nueva.isEstado());

        return reservaEspacioRepository.save(existente);
    }

    // ✅ Eliminar reserva
    public void eliminarReserva(Long id) {
        ReservaEspacio existente = obtenerPorId(id);
        reservaEspacioRepository.delete(existente);
    }

    // ✅ Aprobar reserva (validando traslape antes)
    public ReservaEspacio aprobarReserva(Long id) {
        ReservaEspacio reserva = obtenerPorId(id);

        if (!estaDisponible(reserva.getAula(), reserva.getFecha(), reserva.getHoraInicio(), reserva.getHoraFin())) {
            throw new IllegalArgumentException("No se puede aprobar la reserva, ya existe un conflicto de horario.");
        }

        reserva.setEstado(true);
        return reservaEspacioRepository.save(reserva);
    }

    // ✅ Rechazar reserva
    public ReservaEspacio rechazarReserva(Long id) {
        ReservaEspacio reserva = obtenerPorId(id);
        reserva.setEstado(false);
        return reservaEspacioRepository.save(reserva);
    }
}
