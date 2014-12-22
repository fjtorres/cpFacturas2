package es.fjtorres.cpFacturas.gwtClient.client.application.insurer;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class InsurerListView extends ViewImpl implements InsurerListPresenter.MyView {

    interface Binder extends UiBinder<Widget, InsurerListView> {
    }

    @Inject
    public InsurerListView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
