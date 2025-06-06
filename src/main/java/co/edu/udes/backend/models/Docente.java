package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "docentes")
public class Docente extends Persona {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultad_id", nullable = false)
    private Facultad facultad;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "codigo_institucional", nullable = false, unique = true)
    private String codigoInstitucional;

    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;

    @ManyToMany
    @JoinTable(
            name = "docente_cursos",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos;

    @ManyToMany
    @JoinTable(
            name = "disponibilidad_docente",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    private List<Horario> disponibilidadHoraria;

    public Docente() {}

    public Docente(String nombre, String telefono, String correoPersonal,
                   LocalDate fechaNacimiento, String numeroDocumento, boolean estado,
                   TipoDocumento tipoDocumento, TipoGenero genero, Rol rol,
                   Facultad facultad, String especialidad,
                   String codigoInstitucional, String correoInstitucional) {
        super(nombre, telefono, correoPersonal, fechaNacimiento, numeroDocumento, estado, tipoDocumento, genero, rol);
        this.facultad = facultad;
        this.especialidad = especialidad;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
    }

    // Getters y Setters

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCodigoInstitucional() {
        return codigoInstitucional;
    }

    public void setCodigoInstitucional(String codigoInstitucional) {
        this.codigoInstitucional = codigoInstitucional;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Horario> getDisponibilidadHoraria() {
        return disponibilidadHoraria;
    }

    public void setDisponibilidadHoraria(List<Horario> disponibilidadHoraria) {
        this.disponibilidadHoraria = disponibilidadHoraria;
    }
}
