package es.fjtorres.cpFacturas.gwtClient.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class UnauthorizedView extends ViewImpl implements UnauthorizedPresenter.MyView {

    interface Binder extends UiBinder<Widget, UnauthorizedView> {
    }

    @Inject
    public UnauthorizedView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
