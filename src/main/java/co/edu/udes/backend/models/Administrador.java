package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "administradores")
public class Administrador extends Persona {

    @ManyToOne(fetch = FetchType.EAGER) // 👈 Corrección aplicada aquí
    @JoinColumn(name = "facultad_id", nullable = false)
    private Facultad facultad;

    @Column(name = "codigo_institucional", nullable = false, unique = true)
    private String codigoInstitucional;

    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;

    public Administrador() {
        // Constructor por defecto requerido por JPA
    }

    public Administrador(String nombre, String telefono, String correoPersonal,
                         LocalDate fechaNacimiento, String numeroDocumento, boolean estado,
                         TipoDocumento tipoDocumento, TipoGenero genero, Rol rol,
                         Facultad facultad, String codigoInstitucional, String correoInstitucional) {
        super(nombre, telefono, correoPersonal, fechaNacimiento, numeroDocumento, estado, tipoDocumento, genero, rol);
        this.facultad = facultad;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
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
