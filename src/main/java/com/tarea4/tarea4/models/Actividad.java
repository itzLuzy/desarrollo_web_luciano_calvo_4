package com.tarea4.tarea4.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Actividad {

    @Id
    @SequenceGenerator(
        name = "actividad_sequence",
        sequenceName = "actividad_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "actividad_sequence"
    )
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private Long comuna;

    @NotNull
    private String email;

    @NotNull
    private LocalDateTime diaHoraInicio;

    @NotNull
    private LocalDateTime diaHoraTermino;

    private String celular;

    private String sector;

    private String descripcion;
 
    public Actividad() {
    }

    public Actividad(
                    String nombre,
                    String email,
                    LocalDateTime inicio,
                    LocalDateTime termino,
                    String sector,
                    String celular,
                    String descripcion
                    ) {

        this.nombre = nombre;
        this.email = email;
        this.diaHoraInicio = inicio;
        this.diaHoraTermino = termino;
        this.sector = sector;
        this.celular = celular;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getInicio() {
        return diaHoraInicio;
    }

    public LocalDateTime getTermino() {
        return diaHoraTermino;
    }

    public String getSector() {
        return sector;
    }

    public String getCelular() {
        return celular;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
