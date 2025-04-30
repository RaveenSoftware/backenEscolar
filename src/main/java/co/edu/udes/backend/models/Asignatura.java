package co.edu.udes.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "numero_creditos")
    private int numeroCreditos;

    @Column(name = "numero_semestre")
    private int numeroSemestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pensum_id")
    @JsonBackReference // Evita recursión con pensum.getAsignaturas()
    private Pensum pensum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predecesora_id")
    @JsonIgnoreProperties({"pensum", "predecesora"}) // Previene ciclos infinitos en autoreferencia
    private Asignatura predecesora;

    public Asignatura() {
    }

    public Asignatura(long id, String codigo, String nombre, int numeroCreditos, int numeroSemestre, Pensum pensum, Asignatura predecesora) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroCreditos = numeroCreditos;
        this.numeroSemestre = numeroSemestre;
        this.pensum = pensum;
        this.predecesora = predecesora;
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

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public int getNumeroSemestre() {
        return numeroSemestre;
    }

    public void setNumeroSemestre(int numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public Pensum getPensum() {
        return pensum;
    }

    public void setPensum(Pensum pensum) {
        this.pensum = pensum;
    }

    public Asignatura getPredecesora() {
        return predecesora;
    }

    public void setPredecesora(Asignatura predecesora) {
        this.predecesora = predecesora;
    }
}
