package com.feria.modelo;

import java.util.regex.Pattern;

/**
 * Utilidades de validación.
 */
public final class Validaciones {
    private Validaciones() {}

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static boolean esEmailValido(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
