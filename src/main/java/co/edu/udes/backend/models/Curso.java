package co.edu.udes.backend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "grupo")
    private String grupo;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<AulaHorario> aulaHorarios;

    @ManyToOne
    @JoinColumn(name = "semestre_academico_id")
    private SemestreAcademico semestreAcademico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_academico_id")
    private ProgramaAcademico programaAcademico;

    @ManyToMany
    private List<MatriculaAcademica> matriculaAcademica;

    public Curso() {
    }

    public Curso(long id, Docente docente, String nombre, String grupo, Asignatura asignatura,
                 List<AulaHorario> aulaHorarios, SemestreAcademico semestreAcademico,
                 ProgramaAcademico programaAcademico, List<MatriculaAcademica> matriculaAcademica) {
        this.id = id;
        this.docente = docente;
        this.nombre = nombre;
        this.grupo = grupo;
        this.asignatura = asignatura;
        this.aulaHorarios = aulaHorarios;
        this.semestreAcademico = semestreAcademico;
        this.programaAcademico = programaAcademico;
        this.matriculaAcademica = matriculaAcademica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public List<AulaHorario> getAulaHorarios() {
        return aulaHorarios;
    }

    public void setAulaHorarios(List<AulaHorario> aulaHorarios) {
        this.aulaHorarios = aulaHorarios;
    }

    public SemestreAcademico getSemestreAcademico() {
        return semestreAcademico;
    }

    public void setSemestreAcademico(SemestreAcademico semestreAcademico) {
        this.semestreAcademico = semestreAcademico;
    }

    public ProgramaAcademico getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    public List<MatriculaAcademica> getMatriculaAcademica() {
        return matriculaAcademica;
    }

    public void setMatriculaAcademica(List<MatriculaAcademica> matriculaAcademica) {
        this.matriculaAcademica = matriculaAcademica;
    }
}
