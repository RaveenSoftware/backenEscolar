package co.edu.udes.backend.dto;

import java.time.LocalDateTime;

public class MensajeDTO {
    private Long id;
    private Long remitenteId;
    private Long destinatarioId;
    private String asunto;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private boolean leido;

    // Constructores
    public MensajeDTO() {}

    public MensajeDTO(Long id, Long remitenteId, Long destinatarioId, String asunto,
                     String contenido, LocalDateTime fechaEnvio, boolean leido) {
        this.id = id;
        this.remitenteId = remitenteId;
        this.destinatarioId = destinatarioId;
        this.asunto = asunto;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.leido = leido;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRemitenteId() { return remitenteId; }
    public void setRemitenteId(Long remitenteId) { this.remitenteId = remitenteId; }

    public Long getDestinatarioId() { return destinatarioId; }
    public void setDestinatarioId(Long destinatarioId) { this.destinatarioId = destinatarioId; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public boolean isLeido() { return leido; }
    public void setLeido(boolean leido) { this.leido = leido; }
}