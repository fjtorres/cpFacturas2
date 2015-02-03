package es.fjtorres.cpFacturas.gwtClient.client.application.login;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginUiHandlers {

    public interface MyView extends View , HasUiHandlers<LoginUiHandlers> {

    }

    @NameToken(NameTokens.LOGIN)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<LoginPresenter> {

    }

    @Inject
    public LoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
        
        view.setUiHandlers(this);
    }

    @Override
    public void login(String pUsername, String pPassword) {
        // TODO Auto-generated method stub

    }
}
