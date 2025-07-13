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
public class Foto {

    @Id
    @SequenceGenerator(
        name = "foto_sequence",
        sequenceName = "foto_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "foto_sequence"
    )
    private Integer id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @NotNull
    private String ruta_archivo;

    @NotNull
    private String nombre_archivo;

    public Foto() {
    }

    public Foto(String ruta_archivo, String nombre_archivo, Actividad actividad) {
        this.ruta_archivo = ruta_archivo;
        this.nombre_archivo = nombre_archivo;
        this.actividad = actividad;
    }

    public Integer getId() {
        return id;
    }

    public String getRuta() {
        return ruta_archivo;
    }

    public String getNombre() {
        return nombre_archivo;
    }

    public Actividad getActividad() {
        return actividad;
    }
}
