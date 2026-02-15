package com.feria.main;

import com.feria.gestion.FeriaEmpresarial;
import com.feria.modelo.Empresa;
import com.feria.modelo.TamanoStand;
import com.feria.modelo.Visitante;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Punto de entrada de la aplicación (con menú interactivo por consola).
 */
public class Main {

    private static final Scanner in = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        FeriaEmpresarial feria = new FeriaEmpresarial("Feria Empresarial 2026");
        System.out.println("Bienvenido a " + feria.getNombreFeria());

        boolean salir = false;
        while (!salir) {
            try {
                switch (menuPrincipal()) {
                    case 1: menuEmpresas(feria); break;
                    case 2: menuStands(feria); break;
                    case 3: menuVisitantes(feria); break;
                    case 4: menuInteracciones(feria); break;
                    case 5: menuReportes(feria); break;
                    case 0: salir = true; break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (Exception ex) {
                System.out.println("⚠️  Error: " + ex.getMessage());
            }
        }

        System.out.println("¡Hasta pronto!");
    }

    private static int menuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Empresas");
        System.out.println("2. Stands");
        System.out.println("3. Visitantes");
        System.out.println("4. Interacciones (visitar/comentar)");
        System.out.println("5. Reportes");
        System.out.println("0. Salir");
        System.out.print("Seleccione: ");
        return leerInt();
    }

    // ---------- EMPRESAS ----------
    private static void menuEmpresas(FeriaEmpresarial feria) {
        System.out.println("\n--- EMPRESAS ---");
        System.out.println("1. Registrar");
        System.out.println("2. Editar");
        System.out.println("3. Eliminar");
        System.out.println("4. Listar");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        int op = leerInt();
        switch (op) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = leerLinea();
                System.out.print("Sector (tecnología/salud/educación/etc): ");
                String sector = leerLinea();
                System.out.print("Correo: ");
                String correo = leerLinea();
                feria.registrarEmpresa(new Empresa(nombre, sector, correo));
                System.out.println("✅ Empresa registrada.");
                break;
            case 2:
                System.out.print("Nombre actual: ");
                String actual = leerLinea();
                System.out.print("Nuevo nombre: ");
                String nuevo = leerLinea();
                System.out.print("Nuevo sector: ");
                String nuevoSector = leerLinea();
                System.out.print("Nuevo correo: ");
                String nuevoCorreo = leerLinea();
                feria.editarEmpresa(actual, nuevo, nuevoSector, nuevoCorreo);
                System.out.println("✅ Empresa actualizada.");
                break;
            case 3:
                System.out.print("Nombre de la empresa a eliminar: ");
                String eliminar = leerLinea();
                feria.eliminarEmpresa(eliminar);
                System.out.println("✅ Empresa eliminada (y stand liberado si existía).");
                break;
            case 4:
                System.out.println("Lista de empresas:");
                feria.listarEmpresas().forEach(System.out::println);
                break;
            case 0:
            default:
        }
    }

    // ---------- STANDS ----------
    private static void menuStands(FeriaEmpresarial feria) {
        System.out.println("\n--- STANDS ---");
        System.out.println("1. Registrar stand");
        System.out.println("2. Asignar stand a empresa");
        System.out.println("3. Liberar stand");
        System.out.println("4. Listar disponibles");
        System.out.println("5. Listar ocupados");
        System.out.println("6. Listar todos");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        int op = leerInt();
        switch (op) {
            case 1:
                System.out.print("Número único: ");
                int num = leerInt();
                System.out.print("Ubicación (ej: Pabellón A, Stand 10): ");
                String ubic = leerLinea();
                System.out.print("Tamaño (pequeño/mediano/grande): ");
                String t = leerLinea();
                TamanoStand tam = TamanoStand.fromString(t);
                feria.registrarStand(new com.feria.modelo.Stand(num, ubic, tam));
                System.out.println("✅ Stand registrado.");
                break;
            case 2:
                System.out.print("Número de stand: ");
                int numAsig = leerInt();
                System.out.print("Nombre de la empresa: ");
                String emp = leerLinea();
                feria.asignarStandAEmpresa(numAsig, emp);
                System.out.println("✅ Stand asignado a la empresa.");
                break;
            case 3:
                System.out.print("Número de stand a liberar: ");
                int numLib = leerInt();
                feria.liberarStand(numLib);
                System.out.println("✅ Stand liberado.");
                break;
            case 4:
                System.out.println("Stands disponibles:");
                feria.listarStandsDisponibles().forEach(System.out::println);
                break;
            case 5:
                System.out.println("Stands ocupados:");
                feria.listarStandsOcupados().forEach(System.out::println);
                break;
            case 6:
                System.out.println("Todos los stands:");
                feria.listarStands().forEach(System.out::println);
                break;
            case 0:
            default:
        }
    }

    // ---------- VISITANTES ----------
    private static void menuVisitantes(FeriaEmpresarial feria) {
        System.out.println("\n--- VISITANTES ---");
        System.out.println("1. Registrar visitante");
        System.out.println("2. Editar visitante");
        System.out.println("3. Eliminar visitante");
        System.out.println("4. Listar visitantes");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        int op = leerInt();
        switch (op) {
            case 1:
                System.out.print("Nombre: ");
                String nomV = leerLinea();
                System.out.print("Identificación: ");
                String idV = leerLinea();
                System.out.print("Correo: ");
                String corV = leerLinea();
                feria.registrarVisitante(new Visitante(nomV, idV, corV));
                System.out.println("✅ Visitante registrado.");
                break;
            case 2:
                System.out.print("ID actual: ");
                String idAct = leerLinea();
                System.out.print("Nuevo nombre: ");
                String nNombre = leerLinea();
                System.out.print("Nuevo ID: ");
                String nId = leerLinea();
                System.out.print("Nuevo correo: ");
                String nCorreo = leerLinea();
                feria.editarVisitante(idAct, nNombre, nId, nCorreo);
                System.out.println("✅ Visitante actualizado.");
                break;
            case 3:
                System.out.print("ID del visitante a eliminar: ");
                String idE = leerLinea();
                feria.eliminarVisitante(idE);
                System.out.println("✅ Visitante eliminado.");
                break;
            case 4:
                System.out.println("Lista de visitantes:");
                feria.listarVisitantes().forEach(System.out::println);
                break;
            case 0:
            default:
        }
    }

    // ---------- INTERACCIONES ----------
    private static void menuInteracciones(FeriaEmpresarial feria) {
        System.out.println("\n--- VISITAR STAND Y COMENTAR ---");
        System.out.print("ID del visitante: ");
        String idV = leerLinea();
        System.out.print("Número de stand: ");
        int ns = leerInt();
        System.out.print("Calificación (1-5): ");
        int cal = leerInt();
        System.out.print("Comentario: ");
        String com = leerLinea();
        feria.visitarYComentar(idV, ns, cal, com);
        System.out.println("✅ Comentario registrado.");
    }

    // ---------- REPORTES ----------
    private static void menuReportes(FeriaEmpresarial feria) {
        System.out.println("\n--- REPORTES ---");
        System.out.println("1. Empresas y sus stands");
        System.out.println("2. Visitantes y stands visitados");
        System.out.println("3. Calificación promedio por stand");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");
        int op = leerInt();
        switch (op) {
            case 1:
                System.out.println(feria.reporteEmpresasYStands());
                break;
            case 2:
                System.out.println(feria.reporteVisitantesYVisitas());
                break;
            case 3:
                System.out.println(feria.reportePromediosPorStand());
                break;
            case 0:
            default:
        }
    }

    // ---------- utilidades de entrada ----------
    private static int leerInt() {
        while (true) {
            try {
                int v = in.nextInt();
                in.nextLine(); // limpiar salto
                return v;
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private static String leerLinea() {
        return in.nextLine().trim();
    }
}