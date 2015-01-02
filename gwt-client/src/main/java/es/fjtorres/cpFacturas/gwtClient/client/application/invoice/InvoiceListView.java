package es.fjtorres.cpFacturas.gwtClient.client.application.invoice;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import es.fjtorres.cpFacturas.gwtClient.client.application.invoice.InvoiceListPresenter.MyView;

public class InvoiceListView extends ViewImpl implements MyView {

    interface Binder extends UiBinder<Widget, InvoiceListView> {
    }

    @Inject
    public InvoiceListView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
