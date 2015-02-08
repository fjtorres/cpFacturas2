package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import javax.inject.Inject;

import com.google.gwt.user.client.Cookies;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import es.fjtorres.cpFacturas.gwtClient.client.rpc.AbstractDefaultCallback;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.IAuthenticationRpcAsync;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.ISecuredRpc;

public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView> implements MenuUiHandlers {

    public interface MyView extends View, HasUiHandlers<MenuUiHandlers> {

    }

    private final IAuthenticationRpcAsync authenticationRpc;

    @Inject
    public MenuPresenter(final EventBus eventBus, final MyView view,
            final IAuthenticationRpcAsync pAuthenticationRpc) {
        super(eventBus, view);
        this.authenticationRpc = pAuthenticationRpc;
        view.setUiHandlers(this);
    }

    @Override
    public void logout() {
        authenticationRpc.logout(new AbstractDefaultCallback<Void>(this) {

            @Override
            public void onSuccess(Void pResult) {
                Cookies.removeCookie(ISecuredRpc.AUTH_TOKEN);
            }

        });
    }
}
