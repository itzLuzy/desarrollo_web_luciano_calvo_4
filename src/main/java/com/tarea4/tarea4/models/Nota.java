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
public class Nota {

    @Id
    @SequenceGenerator(
        name = "nota_sequence",
        sequenceName = "nota_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "nota_sequence"
    )
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @NotNull
    private Long nota;

    public Nota() {
    }

    public Nota(Actividad actividad, Long nota) {
        this.actividad = actividad;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public static Boolean validateNota(Long n) {
        return n >= 1 && n <= 7;
    }
}
