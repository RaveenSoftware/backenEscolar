package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Rol;
import co.edu.udes.backend.repositories.RolRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    // Crear un nuevo rol
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Listar todos los roles
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    // Obtener un rol por ID
    public Rol obtenerPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + id));
    }

    // Actualizar un rol
    public Rol actualizar(Long id, Rol nuevo) {
        Rol existente = obtenerPorId(id);
        existente.setNombre(nuevo.getNombre());
        existente.setDescripcion(nuevo.getDescripcion());
        return rolRepository.save(existente);
    }

    // Eliminar un rol
    public void eliminar(Long id) {
        Rol existente = obtenerPorId(id);
        rolRepository.delete(existente);
    }
}
