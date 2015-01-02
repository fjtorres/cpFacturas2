package es.fjtorres.cpFacturas.gwtClient.client.application.insurer;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import es.fjtorres.cpFacturas.gwtClient.client.application.insurer.InsurerListPresenter.MyView;

public class InsurerListView extends ViewImpl implements MyView {

    interface Binder extends UiBinder<Widget, InsurerListView> {
    }

    @Inject
    public InsurerListView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
