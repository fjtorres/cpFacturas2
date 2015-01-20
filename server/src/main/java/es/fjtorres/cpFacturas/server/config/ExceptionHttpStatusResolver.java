package es.fjtorres.cpFacturas.server.config;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;

import es.fjtorres.cpFacturas.server.exceptions.ApiException;

@Provider
public class ExceptionHttpStatusResolver implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(final ApiException exception) {
        Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;

        if (exception instanceof ApiException) {
            httpStatus = Response.Status.BAD_REQUEST;
        } else {
            final Throwable rootCause = ExceptionUtils.getRootCause(exception);
            
            if (rootCause instanceof ConstraintViolationException) {
                httpStatus = Response.Status.BAD_REQUEST;
            }
        }

        return Response.status(httpStatus).entity(exception.getMessage()).build();
    }

}
