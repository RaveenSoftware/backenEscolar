package co.edu.udes.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predecesora_id")
    private Asignatura predecesora;

    @Column(name = "numero_semestre", nullable = false)
    private int numeroSemestre;

    @Column(name = "numero_creditos", nullable = false)
    private int numeroCreditos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pensum_id", nullable = false)
    @JsonBackReference
    private Pensum pensum;

    public Asignatura() {
        // Constructor por defecto requerido por JPA
    }

    public Asignatura(String codigo, String nombre, Asignatura predecesora,
                      int numeroSemestre, int numeroCreditos, Pensum pensum) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.predecesora = predecesora;
        this.numeroSemestre = numeroSemestre;
        this.numeroCreditos = numeroCreditos;
        this.pensum = pensum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura getPredecesora() {
        return predecesora;
    }

    public void setPredecesora(Asignatura predecesora) {
        this.predecesora = predecesora;
    }

    public int getNumeroSemestre() {
        return numeroSemestre;
    }

    public void setNumeroSemestre(int numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public Pensum getPensum() {
        return pensum;
    }

    public void setPensum(Pensum pensum) {
        this.pensum = pensum;
    }
}
