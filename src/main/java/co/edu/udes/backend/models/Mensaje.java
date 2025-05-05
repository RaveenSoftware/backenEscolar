package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remitente_id", nullable = false)
    private Persona remitente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Persona destinatario;

    @Column(name = "asunto", nullable = false)
    private String asunto;

    @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    @Column(name = "leido", nullable = false)
    private boolean leido;

    public Mensaje() {
    }

    public Mensaje(Persona remitente, Persona destinatario, String asunto, String contenido, LocalDateTime fechaEnvio, boolean leido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.leido = leido;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getRemitente() {
        return remitente;
    }

    public void setRemitente(Persona remitente) {
        this.remitente = remitente;
    }

    public Persona getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Persona destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
