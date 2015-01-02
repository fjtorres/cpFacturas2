package es.fjtorres.cpFacturas.gwtClient.client.application.vehicle;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class VehicleListPresenter extends
        Presenter<VehicleListPresenter.MyView, VehicleListPresenter.MyProxy> {

    public interface MyView extends View {

    }

    @NameToken(NameTokens.VEHICLES)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<VehicleListPresenter> {
        
    }
   
    @Inject
    public VehicleListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    }
}
