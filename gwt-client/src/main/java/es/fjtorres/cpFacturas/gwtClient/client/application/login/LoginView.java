package es.fjtorres.cpFacturas.gwtClient.client.application.login;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {

    interface Binder extends UiBinder<Widget, LoginView> {
    }

    @UiField
    protected Button btnLogin;
    
    @UiField
    protected TextBox txtUsername;
    
    @UiField
    protected Input txtPassword;
    
    @Inject
    public LoginView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    @UiHandler("btnLogin")
    public void onClickBtnLogin (final ClickEvent event) {
        event.preventDefault();
        getUiHandlers().login(txtUsername.getText(), txtPassword.getText());
    }

}
