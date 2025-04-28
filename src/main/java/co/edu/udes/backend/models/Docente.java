package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "docentes")
public class Docente extends Persona {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultad_id")
    private Facultad facultad;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "codigo_institucional")
    private String codigoInstitucional;

    @Column(name = "correo_institucional")
    private String correoInstitucional;

    public Docente() {
    }

    public Docente(String nombre, String telefono, String correoPersonal, LocalDate fechaNacimiento,
                   String numeroDocumento, boolean estado, TipoDocumento tipoDocumento, TipoGenero genero,
                   Facultad facultad, String especialidad, String codigoInstitucional, String correoInstitucional) {
        super(0L, nombre, telefono, correoPersonal, fechaNacimiento, numeroDocumento, estado, tipoDocumento, genero);
        this.facultad = facultad;
        this.especialidad = especialidad;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
    }

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
}
