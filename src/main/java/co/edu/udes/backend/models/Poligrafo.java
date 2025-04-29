package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity(name = "poligrafos")
public class Poligrafo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "nota_id")
    private Calificaciones calificaciones;

    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "semestre_id")
    private SemestreAcademico semestreAcademico;

    @Column(name = "creditos_matriculados")
    private int creditosMatriculados;

    @Column(name = "promedio")
    private float promedio;

    @Column(name = "creditos_acumulados")
    private int creditosAcumulados;

    @Column(name = "promedio_acumulado")
    private float promedioAcumulado;

    public Poligrafo() {
    }

    public Poligrafo(long id, Estudiante estudiante, Asignatura asignatura, Calificaciones calificaciones,
                     Date fechaEmision, SemestreAcademico semestreAcademico, int creditosMatriculados,
                     float promedio, int creditosAcumulados, float promedioAcumulado) {
        this.id = id;
        this.estudiante = estudiante;
        this.asignatura = asignatura;
        this.calificaciones = calificaciones;
        this.fechaEmision = fechaEmision;
        this.semestreAcademico = semestreAcademico;
        this.creditosMatriculados = creditosMatriculados;
        this.promedio = promedio;
        this.creditosAcumulados = creditosAcumulados;
        this.promedioAcumulado = promedioAcumulado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Calificaciones getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Calificaciones calificaciones) {
        this.calificaciones = calificaciones;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public SemestreAcademico getSemestreAcademico() {
        return semestreAcademico;
    }

    public void setSemestreAcademico(SemestreAcademico semestreAcademico) {
        this.semestreAcademico = semestreAcademico;
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
