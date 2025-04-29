package co.edu.udes.backend.models;

import jakarta.persistence.*;

@Entity(name = "calificaciones")
public class Calificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "p1", nullable = false)
    private double p1;

    @Column(name = "p2", nullable = false)
    private double p2;

    @Column(name = "definitiva", nullable = false)
    private double definitiva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Calificaciones() {
        // Constructor por defecto requerido por JPA
    }

    public Calificaciones(double p1, double p2, double definitiva, Curso curso) {
        this.p1 = p1;
        this.p2 = p2;
        this.definitiva = definitiva;
        this.curso = curso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getDefinitiva() {
        return definitiva;
    }

    public void setDefinitiva(double definitiva) {
        this.definitiva = definitiva;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
