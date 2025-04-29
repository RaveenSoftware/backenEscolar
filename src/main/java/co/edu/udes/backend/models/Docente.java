package co.edu.udes.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity(
        name = "docentes"
)
public class Docente extends Persona {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @ManyToOne
    @JoinColumn(
            name = "facultad_id"
    )
    private Facultad facultad;
    @Column(
            name = "especialidad"
    )
    private String especialidad;
    @Column(
            name = "codigo_institucional"
    )
    private String codigoInstitucional;
    @Column(
            name = "correo_institucional"
    )
    private String correoInstitucional;

    public Docente() {
    }

    public Docente(long id, String nombre, String telefono, String correoPersonal, LocalDate fechaNacimiento, String numeroDocumento, boolean estado, TipoDocumento tipoDocumento, TipoGenero genero, long id1, Facultad facultad, String especialidad, String codigoInstitucional, String correoInstitucional) {
        this.id = id1;
        this.facultad = facultad;
        this.especialidad = especialidad;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Facultad getFacultad() {
        return this.facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCodigoInstitucional() {
        return this.codigoInstitucional;
    }

    public void setCodigoInstitucional(String codigoInstitucional) {
        this.codigoInstitucional = codigoInstitucional;
    }

    public String getCorreoInstitucional() {
        return this.correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }
}
