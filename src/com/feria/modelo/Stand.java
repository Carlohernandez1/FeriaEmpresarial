package com.feria.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un stand dentro de la feria.
 */
public class Stand {
    private final int numero;
    private String ubicacion; // Ej: "Pabellón A, Stand 10"
    private TamanoStand tamano;
    private Empresa empresaAsignada; // null si está disponible
    private final List<Comentario> comentarios = new ArrayList<>();

    public Stand(int numero, String ubicacion, TamanoStand tamano) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.tamano = tamano;
    }

    public int getNumero() { return numero; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public TamanoStand getTamano() { return tamano; }
    public void setTamano(TamanoStand tamano) { this.tamano = tamano; }

    public Empresa getEmpresaAsignada() { return empresaAsignada; }
    public boolean isDisponible() { return empresaAsignada == null; }

    public void asignarEmpresa(Empresa empresa) {
        this.empresaAsignada = empresa;
    }

    public void liberar() {
        this.empresaAsignada = null;
    }

    public void agregarComentario(Comentario c) {
        this.comentarios.add(c);
    }

    public List<Comentario> getComentarios() { return comentarios; }

    public double promedioCalificacion() {
        if (comentarios.isEmpty()) return 0.0;
        return comentarios.stream().mapToInt(Comentario::getCalificacion).average().orElse(0.0);
    }

    @Override
    public String toString() {
        String empresa = (empresaAsignada == null) ? "—" : empresaAsignada.getNombre();
        return "Stand{" +
                "numero=" + numero +
                ", ubicacion='" + ubicacion + '\'' +
                ", tamano=" + tamano +
                ", empresa=" + empresa +
                ", comentarios=" + comentarios.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stand)) return false;
        Stand stand = (Stand) o;
        return numero == stand.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}