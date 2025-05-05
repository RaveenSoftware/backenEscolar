package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Material;
import co.edu.udes.backend.repositories.MaterialRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    // Crear nuevo material
    public Material crearMaterial(Material material) {
        return materialRepository.save(material);
    }

    // Listar todos
    public List<Material> listarTodos() {
        return materialRepository.findAll();
    }

    // Obtener por ID
    public Material obtenerPorId(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con ID: " + id));
    }

    // Actualizar
    public Material actualizar(Long id, Material nuevo) {
        Material existente = obtenerPorId(id);
        existente.setNombre(nuevo.getNombre());
        existente.setTipo(nuevo.getTipo());
        existente.setDescripcion(nuevo.getDescripcion());
        existente.setDisponible(nuevo.isDisponible());
        return materialRepository.save(existente);
    }

    // Eliminar
    public void eliminar(Long id) {
        Material material = obtenerPorId(id);
        materialRepository.delete(material);
    }

    // Listar solo materiales disponibles
    public List<Material> listarDisponibles() {
        return materialRepository.findByDisponible(true);
    }
}
