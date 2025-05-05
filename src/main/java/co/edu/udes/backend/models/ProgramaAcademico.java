package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "programas_academicos")
public class ProgramaAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo_programa", nullable = false, unique = true)
    private String codigoPrograma;

    @Column(name = "nombre_programa", nullable = false)
    private String nombrePrograma;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultad_id", nullable = false)
    private Facultad facultad;

    @Column(name = "creditos_programa", nullable = false)
    private int creditosPrograma;

    @OneToMany(mappedBy = "programa")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "programa")
    private List<Pensum> pensums;

    public ProgramaAcademico() {}

    public ProgramaAcademico(String codigoPrograma, String nombrePrograma, String descripcion,
                             boolean estado, Facultad facultad, int creditosPrograma) {
        this.codigoPrograma = codigoPrograma;
        this.nombrePrograma = nombrePrograma;
        this.descripcion = descripcion;
        this.estado = estado;
        this.facultad = facultad;
        this.creditosPrograma = creditosPrograma;
    }

    // Getters y Setters

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getCodigoPrograma() { return codigoPrograma; }
    public void setCodigoPrograma(String codigoPrograma) { this.codigoPrograma = codigoPrograma; }

    public String getNombrePrograma() { return nombrePrograma; }
    public void setNombrePrograma(String nombrePrograma) { this.nombrePrograma = nombrePrograma; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }

    public int getCreditosPrograma() { return creditosPrograma; }
    public void setCreditosPrograma(int creditosPrograma) { this.creditosPrograma = creditosPrograma; }

    public List<Curso> getCursos() { return cursos; }
    public void setCursos(List<Curso> cursos) { this.cursos = cursos; }

    public List<Pensum> getPensums() { return pensums; }
    public void setPensums(List<Pensum> pensums) { this.pensums = pensums; }
}
