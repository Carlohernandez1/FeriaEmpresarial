package com.feria.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un visitante de la feria.
 */
public class Visitante {
    private String nombre;
    private String identificacion; // cédula/pasaporte
    private String correoElectronico;

    private final List<Integer> standsVisitados = new ArrayList<>();

    public Visitante(String nombre, String identificacion, String correoElectronico) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        setCorreoElectronico(correoElectronico);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) {
        if (!Validaciones.esEmailValido(correoElectronico)) {
            throw new IllegalArgumentException("Correo electrónico inválido.");
        }
        this.correoElectronico = correoElectronico;
    }

    public List<Integer> getStandsVisitados() { return standsVisitados; }

    /** Registra que este visitante pasó por un stand (para fines de reporte). */
    public void registrarVisita(int numeroStand) {
        standsVisitados.add(numeroStand);
    }

    @Override
    public String toString() {
        return "Visitante{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitante)) return false;
        Visitante v = (Visitante) o;
        return Objects.equals(identificacion, v.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }
}