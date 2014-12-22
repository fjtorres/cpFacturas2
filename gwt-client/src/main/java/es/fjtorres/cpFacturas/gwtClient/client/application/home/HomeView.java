package es.fjtorres.cpFacturas.gwtClient.client.application.home;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    protected Anchor shortcutConfiguration;

    @UiField
    protected Anchor shortcutCustomers;

    @UiField
    protected Anchor shortcutInsurers;

    @UiField
    protected Anchor shortcutInvoices;

    @UiField
    protected Anchor shortcutNewInvoice;

    @UiField
    protected Anchor shortcutVehicles;

    @Inject
    public HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("shortcutConfiguration")
    void onClickConfiguration(final ClickEvent event) {
        getUiHandlers().revealConfiguration();
    }

    @UiHandler("shortcutCustomers")
    void onClickCustomers(final ClickEvent event) {
        getUiHandlers().revealCustomers();
    }

    @UiHandler("shortcutInsurers")
    void onClickInsurers(final ClickEvent event) {
        getUiHandlers().revealInsurers();
    }

    @UiHandler("shortcutInvoices")
    void onClickInvoices(final ClickEvent event) {
        getUiHandlers().revealInvoices();
    }

    @UiHandler("shortcutNewInvoice")
    void onClickNewInvoice(final ClickEvent event) {
        getUiHandlers().revealNewInvoice();
    }

    @UiHandler("shortcutVehicles")
    void onClickVehicles(final ClickEvent event) {
        getUiHandlers().revealVehicles();
    }

}
