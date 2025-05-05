package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "creditos", nullable = false)
    private int creditos;

    @Column(name = "cupo_maximo", nullable = false)
    private int cupoMaximo;

    @Column(name = "inscritos_actuales", nullable = false)
    private int inscritosActuales = 0;

    @ManyToMany
    @JoinTable(
            name = "curso_prerrequisitos",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "prerrequisito_id")
    )
    private List<Curso> prerrequisitos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_id", nullable = false)
    private ProgramaAcademico programa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario horario;

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "objetivos", columnDefinition = "TEXT")
    private String objetivos;

    @Column(name = "competencias", columnDefinition = "TEXT")
    private String competencias;

    @ManyToMany
    @JoinTable(
            name = "docente_cursos",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "docente_id")
    )
    private List<Docente> docentes;

    public Curso() {}

    public Curso(String nombre, String codigo, int creditos, int cupoMaximo, int inscritosActuales,
                 ProgramaAcademico programa, Asignatura asignatura, Horario horario,
                 String contenido, String objetivos, String competencias,
                 List<Docente> docentes, List<Curso> prerrequisitos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
        this.inscritosActuales = inscritosActuales;
        this.programa = programa;
        this.asignatura = asignatura;
        this.horario = horario;
        this.contenido = contenido;
        this.objetivos = objetivos;
        this.competencias = competencias;
        this.docentes = docentes;
        this.prerrequisitos = prerrequisitos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }

    public int getInscritosActuales() { return inscritosActuales; }
    public void setInscritosActuales(int inscritosActuales) { this.inscritosActuales = inscritosActuales; }

    public List<Curso> getPrerrequisitos() { return prerrequisitos; }
    public void setPrerrequisitos(List<Curso> prerrequisitos) { this.prerrequisitos = prerrequisitos; }

    public ProgramaAcademico getPrograma() { return programa; }
    public void setPrograma(ProgramaAcademico programa) { this.programa = programa; }

    public Asignatura getAsignatura() { return asignatura; }
    public void setAsignatura(Asignatura asignatura) { this.asignatura = asignatura; }

    public Horario getHorario() { return horario; }
    public void setHorario(Horario horario) { this.horario = horario; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getObjetivos() { return objetivos; }
    public void setObjetivos(String objetivos) { this.objetivos = objetivos; }

    public String getCompetencias() { return competencias; }
    public void setCompetencias(String competencias) { this.competencias = competencias; }

    public List<Docente> getDocentes() { return docentes; }
    public void setDocentes(List<Docente> docentes) { this.docentes = docentes; }
}
