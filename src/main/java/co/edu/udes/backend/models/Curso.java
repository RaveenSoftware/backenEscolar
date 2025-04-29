package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "grupo", nullable = false)
    private String grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semestre_academico_id", nullable = false)
    private SemestreAcademico semestreAcademico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_academico_id", nullable = false)
    private ProgramaAcademico programaAcademico;

    @ManyToMany
    @JoinTable(
            name = "cursos_matriculas",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "matricula_id")
    )
    private List<MatriculaAcademica> matriculaAcademica;

    public Curso() {
        // Constructor por defecto requerido por JPA
    }

    public Curso(String nombre, String grupo, Docente docente, Asignatura asignatura,
                 SemestreAcademico semestreAcademico, ProgramaAcademico programaAcademico,
                 List<MatriculaAcademica> matriculaAcademica) {
        this.nombre = nombre;
        this.grupo = grupo;
        this.docente = docente;
        this.asignatura = asignatura;
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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
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
