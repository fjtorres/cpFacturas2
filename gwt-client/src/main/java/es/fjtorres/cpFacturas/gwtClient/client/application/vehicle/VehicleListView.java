package es.fjtorres.cpFacturas.gwtClient.client.application.vehicle;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class VehicleListView extends ViewImpl implements VehicleListPresenter.MyView {

    interface Binder extends UiBinder<Widget, VehicleListView> {
    }

    @Inject
    public VehicleListView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
