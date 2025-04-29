package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "programas_academicos")
public class ProgramaAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo_programa")
    private String codigoPrograma;

    @Column(name = "nombre_programa")
    private String nombrePrograma;

    @OneToMany(mappedBy = "programaAcademico", fetch = FetchType.LAZY)
    private List<Pensum> pensums;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "facultad_id")
    private Facultad facultad;

    @Column(name = "creditos_programa")
    private String creditosPrograma;

    @OneToMany(mappedBy = "programaAcademico")
    private List<Curso> cursos;

    public ProgramaAcademico() {
    }

    public ProgramaAcademico(long id, String codigoPrograma, String nombrePrograma, List<Pensum> pensums,
                             String descripcion, boolean estado, Facultad facultad,
                             String creditosPrograma, List<Curso> cursos) {
        this.id = id;
        this.codigoPrograma = codigoPrograma;
        this.nombrePrograma = nombrePrograma;
        this.pensums = pensums;
        this.descripcion = descripcion;
        this.estado = estado;
        this.facultad = facultad;
        this.creditosPrograma = creditosPrograma;
        this.cursos = cursos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public List<Pensum> getPensums() {
        return pensums;
    }

    public void setPensums(List<Pensum> pensums) {
        this.pensums = pensums;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {  // <- este es el getter correcto para boolean
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public String getCreditosPrograma() {
        return creditosPrograma;
    }

    public void setCreditosPrograma(String creditosPrograma) {
        this.creditosPrograma = creditosPrograma;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
