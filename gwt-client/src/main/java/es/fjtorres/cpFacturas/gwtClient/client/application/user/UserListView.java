package es.fjtorres.cpFacturas.gwtClient.client.application.user;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class UserListView extends ViewImpl implements UserListPresenter.MyView {

    interface Binder extends UiBinder<Widget, UserListView> {
    }

    @Inject
    public UserListView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
