package es.fjtorres.cpFacturas.server.api.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public abstract class AbstractResource {

    protected void badRequest(final String message, final Object... parameters) {
        if (message == null || message.trim().isEmpty()) {
            throw new BadRequestException();
        } else {
            badRequest(MessageFormat.format(message, parameters));
        }
    }

    protected void badRequest(final String... messages) {
        if (messages == null || messages.length == 0) {
            throw new BadRequestException();
        } else {
            final Map<String, String[]> error = new HashMap<>();
            error.put("errors", messages);
            throw new BadRequestException(Response.status(Status.BAD_REQUEST).entity(error).build());
        }
    }

    protected void badRequest(final List<String> messages) {
        if (messages != null && !messages.isEmpty()) {
            badRequest(messages.toArray(new String[] {}));
        }
    }
}
