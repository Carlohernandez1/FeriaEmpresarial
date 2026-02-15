package com.feria.modelo;

/**
 * Tamaños posibles para un stand.
 */
public enum TamanoStand {
    PEQUENO, MEDIANO, GRANDE;

    public static TamanoStand fromString(String valor) {
        String v = valor.trim().toUpperCase()
                .replace("Ñ", "N")
                .replace("PEQUEÑO", "PEQUENO");
        switch (v) {
            case "PEQUENO": return PEQUENO;
            case "MEDIANO": return MEDIANO;
            case "GRANDE":  return GRANDE;
            default: throw new IllegalArgumentException("Tamaño inválido: " + valor);
        }
    }
}