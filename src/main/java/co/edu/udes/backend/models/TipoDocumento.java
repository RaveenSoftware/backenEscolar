package co.edu.udes.backend.models;

import jakarta.persistence.*;

@Entity(name = "tipos_documentos")
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    public TipoDocumento() {
        // Constructor por defecto requerido por JPA
    }

    public TipoDocumento(String nombre, boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}
