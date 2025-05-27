package co.edu.udes.backend.dtos;

import java.time.LocalDate;

public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String telefono;
    private String correoPersonal;
    private LocalDate fechaNacimiento;
    private String numeroDocumento;
    private Boolean estado;

    private Long tipoDocumentoId;
    private String tipoDocumentoNombre;

    private Long generoId;
    private String generoNombre;

    private Long rolId;
    private String rolNombre;

    private Long programaId;
    private String programaNombre;

    private String codigoInstitucional;
    private String correoInstitucional;

    private String facultadNombre;

    public EstudianteDTO() {
    }

    public EstudianteDTO(Long id, String nombre, String telefono, String correoPersonal, LocalDate fechaNacimiento, String numeroDocumento, Boolean estado, Long tipoDocumentoId, String tipoDocumentoNombre, Long generoId, String generoNombre, Long rolId, String rolNombre, Long programaId, String programaNombre, String codigoInstitucional, String correoInstitucional, String facultadNombre) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoPersonal = correoPersonal;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroDocumento = numeroDocumento;
        this.estado = estado;
        this.tipoDocumentoId = tipoDocumentoId;
        this.tipoDocumentoNombre = tipoDocumentoNombre;
        this.generoId = generoId;
        this.generoNombre = generoNombre;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.programaId = programaId;
        this.programaNombre = programaNombre;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
        this.facultadNombre = facultadNombre;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public String getTipoDocumentoNombre() {
        return tipoDocumentoNombre;
    }

    public void setTipoDocumentoNombre(String tipoDocumentoNombre) {
        this.tipoDocumentoNombre = tipoDocumentoNombre;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public String getGeneroNombre() {
        return generoNombre;
    }

    public void setGeneroNombre(String generoNombre) {
        this.generoNombre = generoNombre;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Long getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Long programaId) {
        this.programaId = programaId;
    }

    public String getProgramaNombre() {
        return programaNombre;
    }

    public void setProgramaNombre(String programaNombre) {
        this.programaNombre = programaNombre;
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

    public String getFacultadNombre() {
        return facultadNombre;
    }

    public void setFacultadNombre(String facultadNombre) {
        this.facultadNombre = facultadNombre;
    }
}
