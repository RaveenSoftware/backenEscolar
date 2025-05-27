package co.edu.udes.backend.services;

import co.edu.udes.backend.models.Rol;
import co.edu.udes.backend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    public Rol actualizarRol(Long id, Rol rolActualizado) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNombre(rolActualizado.getNombre());
            rol.setEstado(rolActualizado.isEstado());
            return rolRepository.save(rol);
        }).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }
}
