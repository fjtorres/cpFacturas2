package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import org.gwtbootstrap3.client.ui.Alert;
import org.gwtbootstrap3.client.ui.constants.AlertType;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MessagesView extends ViewImpl implements MessagesPresenter.MyView {

    interface Binder extends UiBinder<Widget, MessagesView> {
    }

    @UiField
    protected FlowPanel messagesPanel;

    @Inject
    public MessagesView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void addMessage(final Message pMessage) {
        messagesPanel.clear();

        if (pMessage != null) {
            final Alert alert = new Alert(pMessage.getText());
            alert.setDismissable(true);
            
            switch (pMessage.getStatus()) {
            case ERROR:
                alert.setType(AlertType.DANGER);
                break;
            case INFO:
                alert.setType(AlertType.SUCCESS);
                break;
            default:
                break;
            }
            
            messagesPanel.add(alert);
        }
    }

    @Override
    public void clearMessage() {
        messagesPanel.clear();
    }

}
