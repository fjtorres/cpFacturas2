package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.NavbarLink;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class MenuView extends ViewWithUiHandlers<MenuUiHandlers> implements MenuPresenter.MyView {

    interface Binder extends UiBinder<Widget, MenuView> {
    }

    @UiField
    protected NavbarLink btnLogout;

    @Inject
    public MenuView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("btnLogout")
    public void onClickBtnLogout(ClickEvent pEvent) {
        pEvent.preventDefault();
        getUiHandlers().logout();
    }

}
