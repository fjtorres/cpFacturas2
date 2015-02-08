package es.fjtorres.cpFacturas.gwtClient.client.application.login;

import javax.inject.Inject;

import com.google.gwt.user.client.Cookies;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import es.fjtorres.cpFacturas.common.dto.UserDto;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.AbstractDefaultCallback;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.IAuthenticationRpcAsync;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.ISecuredRpc;
import es.fjtorres.cpFacturas.gwtClient.client.security.CurrentUser;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginUiHandlers {

    public interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
        void setLoginButtonEnabled(boolean enabled);
    }

    @NameToken(NameTokens.LOGIN)
    @NoGatekeeper
    @ProxyStandard
    public interface MyProxy extends ProxyPlace<LoginPresenter> {

    }

    private final PlaceManager placeManager;

    private final IAuthenticationRpcAsync rpc;

    private final CurrentUser currentUser;
    
    @Inject
    public LoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            final PlaceManager pPlaceManager, final IAuthenticationRpcAsync pRpc,
            final CurrentUser pCurrentUser) {
        super(eventBus, view, proxy, RevealType.RootLayout);
        this.placeManager = pPlaceManager;
        this.rpc = pRpc;
        this.currentUser = pCurrentUser;
        view.setUiHandlers(this);
    }

    @Override
    public void login(final String pUsername, final String pPassword) {
        getView().setLoginButtonEnabled(false);
        rpc.login(pUsername, pPassword, new AbstractDefaultCallback<String>(this) {

            @Override
            public void onSuccess(final String pToken) {
                if (pToken != null && !pToken.trim().isEmpty()) {
                    Cookies.setCookie(ISecuredRpc.AUTH_TOKEN, pToken);
                    loggedUserLoad(pToken);
                } else {
                    // TODO AUTHENTICATION ERROR
                    getView().setLoginButtonEnabled(true);
                }
            }

        });
    }

    @Override
    protected void onReveal() {
        rpc.isLoggedUser(new AbstractDefaultCallback<Boolean>(this) {

            @Override
            public void onSuccess(Boolean pResult) {
                if (Boolean.TRUE.equals(pResult)) {
                    goToHome();
                }
            }

        });
    }

    private void loggedUserLoad(final String pToken) {
        rpc.getLoggedUser(new AbstractDefaultCallback<UserDto>(this) {

            @Override
            public void onSuccess(UserDto pResult) {
                currentUser.setLoggedIn(true);
                currentUser.setUser(pResult);
                goToHome();
            }

        });
    }

    private void goToHome() {
        placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.HOME).build());
    }
}
