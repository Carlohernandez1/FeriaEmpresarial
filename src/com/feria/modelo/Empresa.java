package com.feria.modelo;

import java.util.Objects;

/**
 * Representa una empresa participante.
 */
public class Empresa {
    private String nombre;
    private String sector;
    private String correoElectronico;

    public Empresa(String nombre, String sector, String correoElectronico) {
        this.nombre = nombre;
        this.sector = sector;
        setCorreoElectronico(correoElectronico);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) {
        if (!Validaciones.esEmailValido(correoElectronico)) {
            throw new IllegalArgumentException("Correo electrónico inválido.");
        }
        this.correoElectronico = correoElectronico;
    }

    public void mostrarInformacion() {
        System.out.printf("Empresa: %s | Sector: %s | Email: %s%n", nombre, sector, correoElectronico);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", sector='" + sector + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(nombre.toLowerCase(), empresa.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}