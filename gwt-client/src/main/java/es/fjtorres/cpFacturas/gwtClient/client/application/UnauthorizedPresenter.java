package es.fjtorres.cpFacturas.gwtClient.client.application;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class UnauthorizedPresenter extends
        Presenter<UnauthorizedPresenter.MyView, UnauthorizedPresenter.MyProxy> {

    public interface MyView extends View {

    }

    @NameToken(NameTokens.UNAUTHORIZED)
    @NoGatekeeper
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<UnauthorizedPresenter> {

    }

    @Inject
    public UnauthorizedPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.RootLayout);

    }

}
