package es.fjtorres.cpFacturas.gwtClient.client.application.home;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest.Builder;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements
        HomeUiHandlers {

    public interface MyView extends View, HasUiHandlers<HomeUiHandlers> {

    }

    @NameToken(NameTokens.HOME)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<HomePresenter> {

    }

    private PlaceManager placeManager;

    @Inject
    public HomePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            final PlaceManager pPlaceManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
        this.placeManager = pPlaceManager;
        view.setUiHandlers(this);
    }

    @Override
    public void revealNewInvoice() {
        Window.alert("En desarrollo...");
    }

    @Override
    public void revealInvoices() {
        navigate(NameTokens.INVOICES);
    }

    @Override
    public void revealCustomers() {
        navigate(NameTokens.CUSTOMERS);
    }

    @Override
    public void revealInsurers() {
        navigate(NameTokens.INSURERS);
    }

    @Override
    public void revealConfiguration() {
        Window.alert("En desarrollo...");
    }

    @Override
    public void revealVehicles() {
        navigate(NameTokens.VEHICLES);
    }

    private void navigate(final String token) {
        placeManager.revealPlace(new Builder().nameToken(token).build());
    }
}
