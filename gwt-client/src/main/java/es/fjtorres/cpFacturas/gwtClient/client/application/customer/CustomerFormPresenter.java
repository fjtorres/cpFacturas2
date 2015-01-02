package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest.Builder;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.event.CustomerAddEvent;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.AbstractDefaultCallback;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.ICustomerRpcAsync;

public class CustomerFormPresenter extends
        Presenter<CustomerFormPresenter.MyView, CustomerFormPresenter.MyProxy> implements
        CustomerFormUiHandlers {

    public interface MyView extends View, HasUiHandlers<CustomerFormUiHandlers> {
        void edit(CustomerDto dto);
    }

    @NameToken(NameTokens.CUSTOMER_NEW)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<CustomerFormPresenter> {

    }

    private final PlaceManager placeManager;

    private final ICustomerRpcAsync rpc;

    @Inject
    public CustomerFormPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            final ICustomerRpcAsync pRpc, final PlaceManager pPlaceManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);

        this.rpc = pRpc;
        this.placeManager = pPlaceManager;

        view.setUiHandlers(this);
    }

    @Override
    public void onSave(final CustomerDto pDto) {
        rpc.save(pDto, new AbstractDefaultCallback<Void>(this) {

            @Override
            public void onSuccess(Void pResult) {
                CustomerAddEvent.fire(CustomerFormPresenter.this, pDto, pDto.getId() == null);
                onBack();
            }

        });
    }

    @Override
    public void onBack() {
        placeManager.revealPlace(new Builder().nameToken(NameTokens.CUSTOMERS).build());
    }
}
