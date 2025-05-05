package co.edu.udes.backend.models;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.Duration;

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

    @Column(name = "tipo_actividad", nullable = false)
    private String tipoActividad; // CLASE, EXAMEN, TUTORIA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    public Horario() {
    }

    public Horario(LocalTime horaInicio, LocalTime horaFinalizacion, String dia, boolean estado, String tipoActividad, Aula aula) {
        this.horaInicio = horaInicio;
        this.horaFinalizacion = horaFinalizacion;
        this.dia = dia;
        this.estado = estado;
        this.tipoActividad = tipoActividad;
        this.aula = aula;
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

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public int getDuracionHoras() {
        return (int) Duration.between(horaInicio, horaFinalizacion).toHours();
    }
}
