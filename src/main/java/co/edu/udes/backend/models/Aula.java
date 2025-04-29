package co.edu.udes.backend.models;

import jakarta.persistence.*;

@Entity(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "bloque", nullable = false)
    private String bloque;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    public Aula() {
        // Constructor por defecto requerido por JPA
    }

    public Aula(String nombre, String codigo, String bloque, boolean estado) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.bloque = bloque;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
