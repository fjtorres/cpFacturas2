package es.fjtorres.cpFacturas.gwtClient.client.application.insurer;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class InsurerListPresenter extends
        Presenter<InsurerListPresenter.MyView, InsurerListPresenter.MyProxy> {

    public interface MyView extends View {

    }

    @NameToken(NameTokens.INSURERS)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<InsurerListPresenter> {
        
    }
   
    @Inject
    public InsurerListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    }
}
