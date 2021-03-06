package es.fjtorres.cpFacturas.gwtClient.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

    interface Binder extends UiBinder<Widget, ApplicationView> {
    }
    
    @UiField
    protected FlowPanel mainContainer;
    
    @UiField
    protected SimplePanel contentContainer;
    
    @UiField
    protected SimplePanel messagesContainer;
    
    @UiField
    protected SimplePanel menuContainer;
    
    @Inject
    public ApplicationView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    @Override
    public void setInSlot(final Object slot, final IsWidget content) {
        if (slot == ApplicationPresenter.SLOT_MAIN_CONTENT) {
            contentContainer.setWidget(content);
        } else if (slot == ApplicationPresenter.SLOT_MESSAGES_CONTENT) {
            messagesContainer.setWidget(content);
        } else if (slot == ApplicationPresenter.SLOT_MENU_CONTENT) {
            menuContainer.setWidget(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

}
