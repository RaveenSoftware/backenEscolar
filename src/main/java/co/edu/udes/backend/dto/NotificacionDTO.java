package co.edu.udes.backend.dto;

import java.time.LocalDateTime;

public class NotificacionDTO {
    private Long id;
    private String destinatario;
    private String asunto;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private boolean enviado;

    // Constructores
    public NotificacionDTO() {}

    public NotificacionDTO(Long id, String destinatario, String asunto,
                         String mensaje, LocalDateTime fechaEnvio, boolean enviado) {
        this.id = id;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.enviado = enviado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public boolean isEnviado() { return enviado; }
    public void setEnviado(boolean enviado) { this.enviado = enviado; }
}