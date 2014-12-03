package es.fjtorres.cpFacturas.gwtClient.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.MyView  {

    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @Inject
    public HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
