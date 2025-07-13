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
public class Log {

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

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    private String mensaje;


    public Log() {
    }

    public Log(LocalDateTime fecha, String mensaje) {
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public static Boolean validateLog(String s) {
        return (s.length() >= 5) && (s.length() <= 200);
    }
}
