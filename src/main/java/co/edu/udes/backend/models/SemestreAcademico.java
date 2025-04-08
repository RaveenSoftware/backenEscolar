package co.edu.udes.backend.models;

import co.edu.udes.backend.models.Curso;
import co.edu.udes.backend.models.Poligrafo;
import jakarta.persistence.*;

import java.time.Year;
import java.util.List;

@Entity(name = "semestres_academicos")
public class SemestreAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "year")
    private Year year;

    @Column(name = "periodo_academico")
    private String periodoAcademico;

    @Column(name = "descripcion")
    private String descripcion;


    public SemestreAcademico() {
    }

    public SemestreAcademico(long id, Year year, String periodoAcademico, String descripcion) {
        this.id = id;
        this.year = year;
        this.periodoAcademico = periodoAcademico;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}