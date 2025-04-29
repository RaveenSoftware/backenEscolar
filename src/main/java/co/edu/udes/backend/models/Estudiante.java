package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "estudiantes")
public class Estudiante extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private ProgramaAcademico programa;

    @Column(name = "codigo_institucional")
    private String codigoInstitucional;

    @Column(name = "correo_institucional")
    private String correoInstitucional;

    public Estudiante() {
    }

    public Estudiante(long id, String nombre, String telefono, String correoPersonal, LocalDate fechaNacimiento, String numeroDocumento, boolean estado,  long id1, ProgramaAcademico programa, String codigoInstitucional, String correoInstitucional) {
        super();
        this.id = id1;
        this.programa = programa;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public ProgramaAcademico getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaAcademico programa) {
        this.programa = programa;
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