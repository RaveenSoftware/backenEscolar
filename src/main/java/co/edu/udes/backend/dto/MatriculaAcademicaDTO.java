package co.edu.udes.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class MatriculaAcademicaDTO {
    private Long id;
    private String codigo;
    private LocalDate fecha;
    private boolean estado;
    private Long estudianteId;
    private List<Long> cursosIds;
    private String semestre;
    private int creditosActuales;

    // Constructores
    public MatriculaAcademicaDTO() {}

    public MatriculaAcademicaDTO(Long id, String codigo, LocalDate fecha, boolean estado,
                                Long estudianteId, List<Long> cursosIds, String semestre,
                                int creditosActuales) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.estudianteId = estudianteId;
        this.cursosIds = cursosIds;
        this.semestre = semestre;
        this.creditosActuales = creditosActuales;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public List<Long> getCursosIds() {
        return cursosIds;
    }

    public void setCursosIds(List<Long> cursosIds) {
        this.cursosIds = cursosIds;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getCreditosActuales() {
        return creditosActuales;
    }

    public void setCreditosActuales(int creditosActuales) {
        this.creditosActuales = creditosActuales;
    }
}