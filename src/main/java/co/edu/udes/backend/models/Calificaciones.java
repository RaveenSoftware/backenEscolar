package co.edu.udes.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "calificaciones")
public class Calificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p1")
    private Double p1;

    @Column(name = "p2")
    private Double p2;

    @Column(name = "p3")
    private Double p3;

    @Column(name = "definitiva")
    private Double definitiva;

    @Column(name = "comentarios_docente", columnDefinition = "TEXT") // 🚨 NUEVO
    private String comentariosDocente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Calificaciones() {
    }

    public Calificaciones(Double p1, Double p2, Double p3, Estudiante estudiante, Curso curso) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.estudiante = estudiante;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getP1() {
        return p1;
    }

    public void setP1(Double p1) {
        this.p1 = p1;
    }

    public Double getP2() {
        return p2;
    }

    public void setP2(Double p2) {
        this.p2 = p2;
    }

    public Double getP3() {
        return p3;
    }

    public void setP3(Double p3) {
        this.p3 = p3;
    }

    public Double getDefinitiva() {
        return definitiva;
    }

    public void setDefinitiva(Double definitiva) {
        this.definitiva = definitiva;
    }

    public String getComentariosDocente() {
        return comentariosDocente;
    }

    public void setComentariosDocente(String comentariosDocente) {
        this.comentariosDocente = comentariosDocente;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
