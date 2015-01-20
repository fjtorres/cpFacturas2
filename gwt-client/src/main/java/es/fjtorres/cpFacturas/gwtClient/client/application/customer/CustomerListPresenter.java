package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest.Builder;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;
import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.event.CustomerAddEvent;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.event.CustomerAddEvent.CustomerAddHandler;
import es.fjtorres.cpFacturas.gwtClient.client.application.event.DisplayMessageEvent;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.Message;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MessageStatus;
import es.fjtorres.cpFacturas.gwtClient.client.i18n.Customers;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.AbstractDefaultCallback;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.ICustomerRpcAsync;
import es.fjtorres.cpFacturas.gwtClient.client.view.ListView;

public class CustomerListPresenter extends
        Presenter<CustomerListPresenter.MyView, CustomerListPresenter.MyProxy> implements
        CustomerListUiHandlers, CustomerAddHandler {

    public interface MyView extends ListView<CustomerDto, CustomerListUiHandlers> {

    }

    @NameToken(NameTokens.CUSTOMERS)
    @ProxyCodeSplit
    public interface MyProxy extends ProxyPlace<CustomerListPresenter> {

    }

    private final PlaceManager placeManager;

    private final ICustomerRpcAsync rpc;

    private final Customers i18nCustomers;

    @Inject
    public CustomerListPresenter(final EventBus eventBus, final PlaceManager pPlaceManager,
            final MyView view, final MyProxy proxy, final ICustomerRpcAsync pRpc,
            Customers pI18nCustomers) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
        this.placeManager = pPlaceManager;
        this.rpc = pRpc;
        this.i18nCustomers = pI18nCustomers;

        view.setUiHandlers(this);
    }

    @Override
    public void fetchData(final int pStart, final int pSize) {
        rpc.find(pStart / pSize, pSize, new AbstractDefaultCallback<CustomerPageDto>(this) {

            @Override
            public void onSuccess(final CustomerPageDto pResult) {
                getView().displayData(pStart, pResult);
            }

            @Override
            public void afterFailure(final Throwable pCaught) {
                getView().displayData(0, new CustomerPageDto());
            }

        });
    }

    @Override
    protected void onReset() {
        getView().initDataProvider();
    }

    @Override
    public void onAdd(CustomerAddEvent pEvent) {
        String code = "";
        if (pEvent != null && pEvent.getDto() != null && pEvent.getDto().getCode() != null) {
            code = pEvent.getDto().getCode();
        }
        DisplayMessageEvent.fire(this, new Message(i18nCustomers.msg_add_ok(code),
                MessageStatus.INFO));
    }

    @Override
    public void onAdd() {
        placeManager.revealPlace(new Builder().nameToken(NameTokens.CUSTOMER_NEW).build());
    }

    @Override
    public void onEdit(final CustomerDto pSelectedItem) {

    }

    @Override
    public void onDelete(final CustomerDto pSelectedItem) {

    }

    @Override
    public void onSelectionError() {
        DisplayMessageEvent.fire(this, new Message(i18nCustomers.msg_error_selected(),
                MessageStatus.ERROR));
    }
}
