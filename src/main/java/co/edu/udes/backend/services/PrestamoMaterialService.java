package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Material;
import co.edu.udes.backend.models.Persona;
import co.edu.udes.backend.models.PrestamoMaterial;
import co.edu.udes.backend.repositories.MaterialRepository;
import co.edu.udes.backend.repositories.PersonaRepository;
import co.edu.udes.backend.repositories.PrestamoMaterialRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoMaterialService {

    @Autowired
    private PrestamoMaterialRepository prestamoMaterialRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private PersonaRepository personaRepository;

    // ✅ Registrar nuevo préstamo (validando disponibilidad)
    public PrestamoMaterial registrarPrestamo(Long materialId, Long personaId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con ID: " + materialId));
        if (!material.isDisponible()) {
            throw new IllegalStateException("El material no está disponible para préstamo.");
        }

        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + personaId));

        PrestamoMaterial prestamo = new PrestamoMaterial();
        prestamo.setMaterial(material);
        prestamo.setPersona(persona);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setDevuelto(false);

        // Marcar el material como no disponible
        material.setDisponible(false);
        materialRepository.save(material);

        return prestamoMaterialRepository.save(prestamo);
    }

    // ✅ Crear préstamo desde objeto completo (usado por el controlador @PostMapping)
    public PrestamoMaterial crearPrestamo(PrestamoMaterial prestamo) {
        Long materialId = prestamo.getMaterial().getId();
        Long personaId = prestamo.getPersona().getId();
        return registrarPrestamo(materialId, personaId);
    }

    // ✅ Obtener préstamo por ID
    public PrestamoMaterial obtenerPorId(Long id) {
        return prestamoMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo no encontrado con ID: " + id));
    }

    // ✅ Actualizar préstamo
    public PrestamoMaterial actualizarPrestamo(Long id, PrestamoMaterial nuevo) {
        PrestamoMaterial existente = obtenerPorId(id);

        if (!existente.isDevuelto() && nuevo.isDevuelto()) {
            return devolverMaterial(id);
        }

        existente.setFechaDevolucion(nuevo.getFechaDevolucion());
        return prestamoMaterialRepository.save(existente);
    }

    // ✅ Marcar como devuelto
    public PrestamoMaterial marcarComoDevuelto(Long prestamoId) {
        return devolverMaterial(prestamoId);
    }

    // ✅ Devolver material (marcar como devuelto y liberar material)
    public PrestamoMaterial devolverMaterial(Long prestamoId) {
        PrestamoMaterial prestamo = obtenerPorId(prestamoId);

        if (prestamo.isDevuelto()) {
            throw new IllegalStateException("Este material ya fue devuelto.");
        }

        prestamo.setDevuelto(true);
        prestamo.setFechaDevolucion(LocalDate.now());

        Material material = prestamo.getMaterial();
        material.setDisponible(true);
        materialRepository.save(material);

        return prestamoMaterialRepository.save(prestamo);
    }

    // ✅ Eliminar préstamo
    public void eliminarPrestamo(Long id) {
        PrestamoMaterial prestamo = obtenerPorId(id);
        prestamoMaterialRepository.delete(prestamo);
    }

    // ✅ Obtener todos los préstamos
    public List<PrestamoMaterial> obtenerTodos() {
        return prestamoMaterialRepository.findAll();
    }

    // ✅ Obtener préstamos no devueltos
    public List<PrestamoMaterial> obtenerPendientes() {
        return prestamoMaterialRepository.findByDevueltoFalse();
    }

    // ✅ Obtener préstamos por persona
    public List<PrestamoMaterial> obtenerPorPersona(Long personaId) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + personaId));
        return prestamoMaterialRepository.findByPersona(persona);
    }
}
