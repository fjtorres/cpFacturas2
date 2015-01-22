package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;

import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.gwtClient.client.application.event.DisplayMessageEvent;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.Message;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MessageStatus;
import es.fjtorres.cpFacturas.gwtClient.client.i18n.Messages;

public abstract class AbstractDefaultCallback<T> implements AsyncCallback<T> {

   private static final Messages MESSAGES = GWT.create(Messages.class);

   private final HasHandlers hashHandlers;

   private boolean showMessages;

   /**
    * @param pHashHandlers
    */
   public AbstractDefaultCallback(final HasHandlers pHashHandlers) {
      this(pHashHandlers, true);
   }

   /**
    * @param pHashHandlers
    */
   public AbstractDefaultCallback(final HasHandlers pHashHandlers,
         final boolean pShowMessages) {
      hashHandlers = pHashHandlers;
      this.showMessages = pShowMessages;
   }

   @Override
   public void onFailure(final Throwable pCaught) {
      if (showMessages) {
         String message = MESSAGES.error_default();
         String[] messages = null;

         if (pCaught instanceof ValidationException) {
            messages = ((ValidationException) pCaught).getErrors().toArray(
                  new String[] {

                  });
         } else if (pCaught instanceof AppException) {
            message = pCaught.getMessage();
         } else if (pCaught instanceof StatusCodeException) {
            final StatusCodeException sce = (StatusCodeException) pCaught;
            switch (sce.getStatusCode()) {
               case Response.SC_INTERNAL_SERVER_ERROR:
                  message = MESSAGES.error_remoteCall();
                  break;
               default:
                  message = sce.getStatusText();
                  break;
            }
         }

         if (messages == null || messages.length == 0) {
            DisplayMessageEvent.fire(hashHandlers, new Message(
                  MessageStatus.ERROR, message));
         } else {
            DisplayMessageEvent.fire(hashHandlers, new Message(
                  MessageStatus.ERROR, messages));
         }

      }

      afterFailure(pCaught);
   }

   public void afterFailure(final Throwable pCaught) {

   }

}
