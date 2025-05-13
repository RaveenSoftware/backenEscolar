package co.edu.udes.backend.dto;

import java.util.List;

public class CursoDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private int creditos;
    private int cupoMaximo;
    private int inscritosActuales;
    private Long programaId;
    private Long asignaturaId;
    private Long horarioId;
    private String contenido;
    private String objetivos;
    private String competencias;
    private List<Long> docentesIds;
    private List<Long> prerequisitosIds;

    // Constructores
    public CursoDTO() {}

    public CursoDTO(Long id, String nombre, String codigo, int creditos, int cupoMaximo,
                    int inscritosActuales, Long programaId, Long asignaturaId, Long horarioId,
                    String contenido, String objetivos, String competencias,
                    List<Long> docentesIds, List<Long> prerequisitosIds) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
        this.inscritosActuales = inscritosActuales;
        this.programaId = programaId;
        this.asignaturaId = asignaturaId;
        this.horarioId = horarioId;
        this.contenido = contenido;
        this.objetivos = objetivos;
        this.competencias = competencias;
        this.docentesIds = docentesIds;
        this.prerequisitosIds = prerequisitosIds;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getInscritosActuales() {
        return inscritosActuales;
    }

    public void setInscritosActuales(int inscritosActuales) {
        this.inscritosActuales = inscritosActuales;
    }

    public Long getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Long programaId) {
        this.programaId = programaId;
    }

    public Long getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Long asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Long getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Long horarioId) {
        this.horarioId = horarioId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getCompetencias() {
        return competencias;
    }

    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }

    public List<Long> getDocentesIds() {
        return docentesIds;
    }

    public void setDocentesIds(List<Long> docentesIds) {
        this.docentesIds = docentesIds;
    }

    public List<Long> getPrerequisitosIds() {
        return prerequisitosIds;
    }

    public void setPrerequisitosIds(List<Long> prerequisitosIds) {
        this.prerequisitosIds = prerequisitosIds;
    }
}