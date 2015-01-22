package es.fjtorres.cpFacturas.common.exception;

public class EntityNotFoundException extends AppException {

   private static final long serialVersionUID = 6994732986611619135L;

   public EntityNotFoundException(final String pMessage, final Throwable pCause) {
      super(pMessage, pCause);
   }

   public EntityNotFoundException(final String pMessage) {
      super(pMessage);
   }

   public EntityNotFoundException() {

   }
}
