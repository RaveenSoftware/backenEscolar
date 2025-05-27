package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "estudiantes")
public class Estudiante extends Persona {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programa_id", nullable = false)
    private ProgramaAcademico programa;

    @Column(name = "codigo_institucional", nullable = false, unique = true)
    private String codigoInstitucional;

    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;

    public Estudiante() {
        // Constructor por defecto requerido por JPA
    }

    public Estudiante(String nombre, String telefono, String correoPersonal,
                      LocalDate fechaNacimiento, String numeroDocumento, boolean estado,
                      TipoDocumento tipoDocumento, TipoGenero genero, Rol rol,
                      ProgramaAcademico programa, String codigoInstitucional,
                      String correoInstitucional) {
        super(nombre, telefono, correoPersonal, fechaNacimiento, numeroDocumento, estado, tipoDocumento, genero, rol);
        this.programa = programa;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
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
