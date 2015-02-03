package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class MenuView extends ViewImpl implements MenuPresenter.MyView {

    interface Binder extends UiBinder<Widget, MenuView> {
    }

    @Inject
    public MenuView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
