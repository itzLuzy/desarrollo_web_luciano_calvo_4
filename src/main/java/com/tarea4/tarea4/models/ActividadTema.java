package com.tarea4.tarea4.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class ActividadTema{

    @Id
    @SequenceGenerator(
        name = "tema_sequence",
        sequenceName = "tema_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tema_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @NotNull
    private String tema;

    private String glosa_otro;

    public ActividadTema() {
    }

    public ActividadTema(
                    Actividad actividad, 
                    String tema,
                    String glosa_otro
                    ) {

        this.actividad = actividad;
        this.tema = tema;
        this.glosa_otro = glosa_otro;
    }

    public Long getId() {
        return id;
    }

    public String getTema() {
        if (glosa_otro != null) {
            return glosa_otro;
        }
        return tema;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
}

