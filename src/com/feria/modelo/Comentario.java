package com.feria.modelo;

import java.time.LocalDate;

/**
 * Comentario y calificación dejados por un visitante a un stand.
 */
public class Comentario {
    private final String nombreVisitante;
    private final LocalDate fecha;
    private final int calificacion; // 1 a 5
    private final String comentario;

    public Comentario(String nombreVisitante, LocalDate fecha, int calificacion, String comentario) {
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe ser entre 1 y 5.");
        }
        this.nombreVisitante = nombreVisitante;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public String getNombreVisitante() { return nombreVisitante; }
    public LocalDate getFecha() { return fecha; }
    public int getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }

    @Override
    public String toString() {
        return "Comentario{" +
                "visitante='" + nombreVisitante + '\'' +
                ", fecha=" + fecha +
                ", calificación=" + calificacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}