package com.feria.modelo;

import java.time.LocalDate;

/**
 * Registro de una visita hecha por un visitante a un stand (para auditoría/reportes).
 */
public class Visita {
    private final String idVisitante;
    private final int numeroStand;
    private final LocalDate fecha;
    private final int calificacion;
    private final String comentario;

    public Visita(String idVisitante, int numeroStand, LocalDate fecha, int calificacion, String comentario) {
        this.idVisitante = idVisitante;
        this.numeroStand = numeroStand;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public String getIdVisitante() { return idVisitante; }
    public int getNumeroStand() { return numeroStand; }
    public LocalDate getFecha() { return fecha; }
    public int getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }
}