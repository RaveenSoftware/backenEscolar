package co.edu.udes.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "materiales")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    private String tipo; // Ej: libro, equipo, etc.

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "disponible", nullable = false)
    private boolean disponible;

    public Material() {
    }

    public Material(String nombre, String tipo, String descripcion, boolean disponible) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
