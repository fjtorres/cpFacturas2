package es.fjtorres.cpFacturas.server.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.fjtorres.cpFacturas.server.exceptions.ApiException;

@Provider
public class ExceptionHttpStatusResolver implements ExceptionMapper<Exception> {

   private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHttpStatusResolver.class);
   
   @Override
   public Response toResponse(final Exception exception) {
      Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;

      LOGGER.error("Error in rest call", exception);
      
      if (exception instanceof ApiException) {
         httpStatus = Response.Status.BAD_REQUEST;
      } else {
         final Throwable rootCause = ExceptionUtils.getRootCause(exception);

         if (rootCause instanceof ConstraintViolationException) {
            httpStatus = Response.Status.BAD_REQUEST;
         } else if (exception instanceof NullPointerException
               || exception instanceof IllegalArgumentException) {
            httpStatus = Response.Status.BAD_REQUEST;
         }
      }

      final Map<String, String[]> error = new HashMap<>();
      error.put("errors", new String[] {
         exception.getMessage()
      });
      return Response.status(httpStatus).entity(error).build();
   }

}
