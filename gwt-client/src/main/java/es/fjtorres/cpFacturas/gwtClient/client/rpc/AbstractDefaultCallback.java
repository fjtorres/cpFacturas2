package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;

import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.gwtClient.client.application.event.DisplayMessageEvent;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.Message;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MessageStatus;
import es.fjtorres.cpFacturas.gwtClient.client.i18n.Messages;

public abstract class AbstractDefaultCallback<T> implements AsyncCallback<T> {

    private static final Messages MESSAGES = GWT.create(Messages.class);

    private final HasHandlers hashHandlers;

    /**
     * @param pHashHandlers
     */
    public AbstractDefaultCallback(final HasHandlers pHashHandlers) {
        hashHandlers = pHashHandlers;
    }

    @Override
    public void onFailure(final Throwable pCaught) {
        String message = MESSAGES.error_default();

        if (pCaught instanceof AppException) {
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
        
        DisplayMessageEvent.fire(hashHandlers, new Message(message, MessageStatus.ERROR));

        failure();
    }

    public void failure() {

    }

}
