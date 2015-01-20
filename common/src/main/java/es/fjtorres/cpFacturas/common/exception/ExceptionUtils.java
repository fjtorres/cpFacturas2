package es.fjtorres.cpFacturas.common.exception;

import java.text.MessageFormat;

public final class ExceptionUtils {

    private ExceptionUtils() {
        // Blank
    }

    public static void throwIllegalArgument(final String message, Object... parameters) {
        throw new IllegalArgumentException(MessageFormat.format(message, parameters));
    }
}
