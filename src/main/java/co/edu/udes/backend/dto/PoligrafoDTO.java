package co.edu.udes.backend.dto;

import java.sql.Date;

public class PoligrafoDTO {
    private Long id;
    private Long estudianteId;
    private Long asignaturaId;
    private Long calificacionesId;
    private Date fechaEmision;
    private Long semestreAcademicoId;
    private int creditosMatriculados;
    private float promedio;
    private int creditosAcumulados;
    private float promedioAcumulado;

    // Constructores
    public PoligrafoDTO() {}

    public PoligrafoDTO(Long id, Long estudianteId, Long asignaturaId, Long calificacionesId,
                       Date fechaEmision, Long semestreAcademicoId, int creditosMatriculados,
                       float promedio, int creditosAcumulados, float promedioAcumulado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.asignaturaId = asignaturaId;
        this.calificacionesId = calificacionesId;
        this.fechaEmision = fechaEmision;
        this.semestreAcademicoId = semestreAcademicoId;
        this.creditosMatriculados = creditosMatriculados;
        this.promedio = promedio;
        this.creditosAcumulados = creditosAcumulados;
        this.promedioAcumulado = promedioAcumulado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Long asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Long getCalificacionesId() {
        return calificacionesId;
    }

    public void setCalificacionesId(Long calificacionesId) {
        this.calificacionesId = calificacionesId;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Long getSemestreAcademicoId() {
        return semestreAcademicoId;
    }

    public void setSemestreAcademicoId(Long semestreAcademicoId) {
        this.semestreAcademicoId = semestreAcademicoId;
    }

    public int getCreditosMatriculados() {
        return creditosMatriculados;
    }

    public void setCreditosMatriculados(int creditosMatriculados) {
        this.creditosMatriculados = creditosMatriculados;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public int getCreditosAcumulados() {
        return creditosAcumulados;
    }

    public void setCreditosAcumulados(int creditosAcumulados) {
        this.creditosAcumulados = creditosAcumulados;
    }

    public float getPromedioAcumulado() {
        return promedioAcumulado;
    }

    public void setPromedioAcumulado(float promedioAcumulado) {
        this.promedioAcumulado = promedioAcumulado;
    }
}