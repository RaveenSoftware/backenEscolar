package co.edu.udes.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "pensums")
public class Pensum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo_pensum")
    private String codigoPensum;

    @OneToMany(mappedBy = "pensum", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("pensum") // Evita ciclos infinitos
    private List<Asignatura> asignaturas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_academico_id")
    private ProgramaAcademico programaAcademico;

    @Column(name = "estado")
    private boolean estado;

    public Pensum() {
    }

    public Pensum(long id, String codigoPensum, List<Asignatura> asignaturas, ProgramaAcademico programaAcademico, boolean estado) {
        this.id = id;
        this.codigoPensum = codigoPensum;
        this.asignaturas = asignaturas;
        this.programaAcademico = programaAcademico;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoPensum() {
        return codigoPensum;
    }

    public void setCodigoPensum(String codigoPensum) {
        this.codigoPensum = codigoPensum;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public ProgramaAcademico getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
