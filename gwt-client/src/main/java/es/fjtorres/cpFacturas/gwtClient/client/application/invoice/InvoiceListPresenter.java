package es.fjtorres.cpFacturas.gwtClient.client.application.invoice;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class InvoiceListPresenter extends
        Presenter<InvoiceListPresenter.MyView, InvoiceListPresenter.MyProxy> {

    public interface MyView extends View {

    }

    @NameToken(NameTokens.INVOICES)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<InvoiceListPresenter> {
        
    }
   
    @Inject
    public InvoiceListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    }
}
