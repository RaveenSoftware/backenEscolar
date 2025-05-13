package co.edu.udes.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class DocenteDTO {
    private Long id;
    private String nombre;
    private String telefono;
    private String correoPersonal;
    private LocalDate fechaNacimiento;
    private String numeroDocumento;
    private boolean estado;
    private Long tipoDocumentoId;
    private Long tipoGeneroId;
    private Long facultadId;
    private String especialidad;
    private String codigoInstitucional;
    private String correoInstitucional;
    private List<Long> cursosIds;
    private List<Long> disponibilidadHorariaIds;

    // Constructores
    public DocenteDTO() {}

    public DocenteDTO(Long id, String nombre, String telefono, String correoPersonal,
                     LocalDate fechaNacimiento, String numeroDocumento, boolean estado,
                     Long tipoDocumentoId, Long tipoGeneroId, Long facultadId,
                     String especialidad, String codigoInstitucional,
                     String correoInstitucional, List<Long> cursosIds,
                     List<Long> disponibilidadHorariaIds) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoPersonal = correoPersonal;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroDocumento = numeroDocumento;
        this.estado = estado;
        this.tipoDocumentoId = tipoDocumentoId;
        this.tipoGeneroId = tipoGeneroId;
        this.facultadId = facultadId;
        this.especialidad = especialidad;
        this.codigoInstitucional = codigoInstitucional;
        this.correoInstitucional = correoInstitucional;
        this.cursosIds = cursosIds;
        this.disponibilidadHorariaIds = disponibilidadHorariaIds;
    }

    // Getters y Setters
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public Long getTipoGeneroId() {
        return tipoGeneroId;
    }

    public void setTipoGeneroId(Long tipoGeneroId) {
        this.tipoGeneroId = tipoGeneroId;
    }

    public Long getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Long facultadId) {
        this.facultadId = facultadId;
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

    public List<Long> getCursosIds() {
        return cursosIds;
    }

    public void setCursosIds(List<Long> cursosIds) {
        this.cursosIds = cursosIds;
    }

    public List<Long> getDisponibilidadHorariaIds() {
        return disponibilidadHorariaIds;
    }

    public void setDisponibilidadHorariaIds(List<Long> disponibilidadHorariaIds) {
        this.disponibilidadHorariaIds = disponibilidadHorariaIds;
    }
}