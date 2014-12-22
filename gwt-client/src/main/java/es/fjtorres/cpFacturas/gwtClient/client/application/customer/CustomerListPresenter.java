package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class CustomerListPresenter extends
        Presenter<CustomerListPresenter.MyView, CustomerListPresenter.MyProxy> {

    public interface MyView extends View {

    }

    @NameToken(NameTokens.CUSTOMERS)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<CustomerListPresenter> {
        
    }
   
    @Inject
    public CustomerListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    }
}
