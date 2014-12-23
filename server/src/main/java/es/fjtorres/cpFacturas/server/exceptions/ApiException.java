package es.fjtorres.cpFacturas.server.exceptions;

public class ApiException extends RuntimeException {

   private static final long serialVersionUID = 8880714437296122687L;

   public ApiException() {
      super();
   }

   public ApiException(final String message, final Throwable cause) {
      super(message, cause);
   }

   public ApiException(final String message) {
      super(message);
   }

   public ApiException(final Throwable cause) {
      super(cause);
   }

}
