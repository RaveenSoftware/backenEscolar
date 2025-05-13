package co.edu.udes.backend.dto;

import java.time.LocalDate;

public class EvaluacionDTO {
    private Long id;
    private String tipo;
    private LocalDate fecha;
    private Double nota;
    private Long estudianteId;
    private Long asignaturaId;
    private String observaciones;

    // Constructores
    public EvaluacionDTO() {}

    public EvaluacionDTO(Long id, String tipo, LocalDate fecha, Double nota,
                        Long estudianteId, Long asignaturaId, String observaciones) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.nota = nota;
        this.estudianteId = estudianteId;
        this.asignaturaId = asignaturaId;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public Long getAsignaturaId() { return asignaturaId; }
    public void setAsignaturaId(Long asignaturaId) { this.asignaturaId = asignaturaId; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}