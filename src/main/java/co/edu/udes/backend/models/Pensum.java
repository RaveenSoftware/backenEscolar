package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pensums")
public class Pensum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_pensum", nullable = false, unique = true)
    private String codigoPensum;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_id", nullable = false)
    private ProgramaAcademico programa;

    @ManyToMany
    @JoinTable(
            name = "pensum_asignaturas",
            joinColumns = @JoinColumn(name = "pensum_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private List<Asignatura> asignaturas;

    public Pensum() {}

    public Pensum(String codigoPensum, String nombre, boolean estado, ProgramaAcademico programa, List<Asignatura> asignaturas) {
        this.codigoPensum = codigoPensum;
        this.nombre = nombre;
        this.estado = estado;
        this.programa = programa;
        this.asignaturas = asignaturas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoPensum() {
        return codigoPensum;
    }

    public void setCodigoPensum(String codigoPensum) {
        this.codigoPensum = codigoPensum;
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

    public ProgramaAcademico getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaAcademico programa) {
        this.programa = programa;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
