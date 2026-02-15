package com.feria.gestion;

import com.feria.modelo.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Núcleo de la aplicación: gestiona empresas, stands, visitantes e interacciones.
 */
public class FeriaEmpresarial {

    private final String nombreFeria;
    private final Map<String, Empresa> empresas = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private final Map<Integer, Stand> stands = new TreeMap<>();
    private final Map<String, Visitante> visitantes = new TreeMap<>();
    private final List<Visita> historialVisitas = new ArrayList<>();

    public FeriaEmpresarial(String nombreFeria) {
        this.nombreFeria = nombreFeria;
    }

    // ================= EMPRESAS =================

    public void registrarEmpresa(Empresa e) {
        if (empresas.containsKey(e.getNombre())) {
            throw new IllegalArgumentException("Ya existe una empresa con ese nombre.");
        }
        empresas.put(e.getNombre(), e);
    }

    public void editarEmpresa(String nombreActual, String nuevoNombre, String nuevoSector, String nuevoEmail) {
        Empresa e = empresas.remove(nombreActual);
        if (e == null) throw new NoSuchElementException("No existe la empresa: " + nombreActual);
        e.setNombre(nuevoNombre);
        e.setSector(nuevoSector);
        e.setCorreoElectronico(nuevoEmail);
        empresas.put(e.getNombre(), e);
    }

    public void eliminarEmpresa(String nombre) {
        Empresa e = empresas.remove(nombre);
        if (e == null) throw new NoSuchElementException("No existe la empresa: " + nombre);
        // Liberar cualquier stand asignado a esta empresa
        for (Stand s : stands.values()) {
            if (e.equals(s.getEmpresaAsignada())) {
                s.liberar();
            }
        }
    }

    public Collection<Empresa> listarEmpresas() {
        return empresas.values();
    }

    // ================= STANDS =================

    public void registrarStand(Stand s) {
        if (stands.containsKey(s.getNumero())) {
            throw new IllegalArgumentException("Ya existe un stand con ese número.");
        }
        stands.put(s.getNumero(), s);
    }

    public Collection<Stand> listarStands() {
        return stands.values();
    }

    public List<Stand> listarStandsDisponibles() {
        List<Stand> libres = new ArrayList<>();
        for (Stand s : stands.values()) if (s.isDisponible()) libres.add(s);
        return libres;
    }

    public List<Stand> listarStandsOcupados() {
        List<Stand> ocupados = new ArrayList<>();
        for (Stand s : stands.values()) if (!s.isDisponible()) ocupados.add(s);
        return ocupados;
    }

    public void asignarStandAEmpresa(int numeroStand, String nombreEmpresa) {
        Stand stand = stands.get(numeroStand);
        if (stand == null) throw new NoSuchElementException("No existe el stand: " + numeroStand);
        Empresa empresa = empresas.get(nombreEmpresa);
        if (empresa == null) throw new NoSuchElementException("No existe la empresa: " + nombreEmpresa);

        if (!stand.isDisponible()) {
            throw new IllegalStateException("El stand ya está asignado a: " +
                    stand.getEmpresaAsignada().getNombre());
        }
        stand.asignarEmpresa(empresa);
    }

    public void liberarStand(int numeroStand) {
        Stand stand = stands.get(numeroStand);
        if (stand == null) throw new NoSuchElementException("No existe el stand: " + numeroStand);
        stand.liberar();
    }

    // ================= VISITANTES =================

    public void registrarVisitante(Visitante v) {
        if (visitantes.containsKey(v.getIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un visitante con esa identificación.");
        }
        visitantes.put(v.getIdentificacion(), v);
    }

    public void editarVisitante(String idActual, String nuevoNombre, String nuevoId, String nuevoEmail) {
        Visitante v = visitantes.remove(idActual);
        if (v == null) throw new NoSuchElementException("No existe el visitante con ID: " + idActual);
        v.setNombre(nuevoNombre);
        v.setIdentificacion(nuevoId);
        v.setCorreoElectronico(nuevoEmail);
        visitantes.put(v.getIdentificacion(), v);
    }

    public void eliminarVisitante(String identificacion) {
        Visitante v = visitantes.remove(identificacion);
        if (v == null) throw new NoSuchElementException("No existe el visitante con ID: " + identificacion);
        // No se borran sus comentarios históricos.
    }

    public Collection<Visitante> listarVisitantes() {
        return visitantes.values();
    }

    // ================= INTERACCIONES =================

    /** Un visitante visita un stand y deja comentario/calificación. */
    public void visitarYComentar(String idVisitante, int numeroStand, int calificacion, String comentario) {
        Visitante v = visitantes.get(idVisitante);
        if (v == null) throw new NoSuchElementException("No existe el visitante con ID: " + idVisitante);

        Stand s = stands.get(numeroStand);
        if (s == null) throw new NoSuchElementException("No existe el stand: " + numeroStand);

        Comentario c = new Comentario(v.getNombre(), LocalDate.now(), calificacion, comentario);
        s.agregarComentario(c);
        v.registrarVisita(numeroStand);
        historialVisitas.add(new Visita(idVisitante, numeroStand, LocalDate.now(), calificacion, comentario));
    }

    // ================= REPORTES =================

    /** (1) Empresas registradas y sus stands asignados. */
    public String reporteEmpresasYStands() {
        StringBuilder sb = new StringBuilder("=== Empresas y Stands ===\n");
        for (Empresa e : empresas.values()) {
            Optional<Stand> st = stands.values().stream()
                    .filter(s -> e.equals(s.getEmpresaAsignada()))
                    .findFirst();
            sb.append("- ").append(e.getNombre())
                    .append(" | Sector: ").append(e.getSector())
                    .append(" | Stand: ").append(st.map(s -> s.getNumero() + " (" + s.getUbicacion() + ")").orElse("Sin asignar"))
                    .append("\n");
        }
        return sb.toString();
    }

    /** (2) Visitantes y stands visitados. */
    public String reporteVisitantesYVisitas() {
        StringBuilder sb = new StringBuilder("=== Visitantes y Stands Visitados ===\n");
        for (Visitante v : visitantes.values()) {
            sb.append("- ").append(v.getNombre()).append(" [").append(v.getIdentificacion()).append("]: ");
            if (v.getStandsVisitados().isEmpty()) {
                sb.append("Sin visitas registradas");
            } else {
                sb.append(v.getStandsVisitados());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /** (3) Calificación promedio por stand. */
    public String reportePromediosPorStand() {
        StringBuilder sb = new StringBuilder("=== Calificación Promedio por Stand ===\n");
        for (Stand s : stands.values()) {
            sb.append("- Stand ").append(s.getNumero())
                    .append(" (").append(s.getUbicacion()).append("): ")
                    .append(String.format(Locale.US, "%.2f", s.promedioCalificacion()))
                    .append(" ⭐ (").append(s.getComentarios().size()).append(" comentarios)")
                    .append("\n");
        }
        return sb.toString();
    }

    public String getNombreFeria() {
        return nombreFeria;
    }
}