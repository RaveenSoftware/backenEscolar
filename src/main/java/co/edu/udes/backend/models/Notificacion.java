package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinatario; // Correo electrónico

    private String asunto;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    private LocalDateTime fechaEnvio;

    private boolean enviado;

    public Notificacion() {}

    public Notificacion(String destinatario, String asunto, String mensaje, LocalDateTime fechaEnvio, boolean enviado) {
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
