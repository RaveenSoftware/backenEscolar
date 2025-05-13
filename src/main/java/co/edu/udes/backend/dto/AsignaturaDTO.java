package co.edu.udes.backend.dto;

public class AsignaturaDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private int numeroCreditos;
    private int numeroSemestre;
    private Long pensumId;
    private Long predecesoraId;

    // Constructores
    public AsignaturaDTO() {}

    public AsignaturaDTO(Long id, String codigo, String nombre, int numeroCreditos, 
                        int numeroSemestre, Long pensumId, Long predecesoraId) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroCreditos = numeroCreditos;
        this.numeroSemestre = numeroSemestre;
        this.pensumId = pensumId;
        this.predecesoraId = predecesoraId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public int getNumeroSemestre() {
        return numeroSemestre;
    }

    public void setNumeroSemestre(int numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public Long getPensumId() {
        return pensumId;
    }

    public void setPensumId(Long pensumId) {
        this.pensumId = pensumId;
    }

    public Long getPredecesoraId() {
        return predecesoraId;
    }

    public void setPredecesoraId(Long predecesoraId) {
        this.predecesoraId = predecesoraId;
    }
}