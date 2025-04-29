package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_finalizacion", nullable = false)
    private LocalTime horaFinalizacion;

    @Column(name = "dia", nullable = false)
    private String dia;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    public Horario() {
        // Constructor por defecto requerido por JPA
    }

    public Horario(LocalTime horaInicio, LocalTime horaFinalizacion, String dia, boolean estado) {
        this.horaInicio = horaInicio;
        this.horaFinalizacion = horaFinalizacion;
        this.dia = dia;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(LocalTime horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
