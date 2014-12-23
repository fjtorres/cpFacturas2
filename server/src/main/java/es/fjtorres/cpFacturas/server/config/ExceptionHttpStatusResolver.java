package es.fjtorres.cpFacturas.server.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.fjtorres.cpFacturas.server.exceptions.ApiException;

@Provider
public class ExceptionHttpStatusResolver implements ExceptionMapper<Throwable> {

   @Override
   public Response toResponse(final Throwable exception) {
      Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;

      if (exception instanceof ApiException) {
         httpStatus = Response.Status.BAD_REQUEST;
      }

      return Response.status(httpStatus).entity(exception.getMessage()).build();
   }

}
