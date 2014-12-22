package es.fjtorres.cpFacturas.gwtClient.client.application.home;

import com.gwtplatform.mvp.client.UiHandlers;

public interface HomeUiHandlers extends UiHandlers {

    void revealNewInvoice();

    void revealInvoices();

    void revealCustomers();

    void revealInsurers();

    void revealConfiguration();

    void revealVehicles();
}
