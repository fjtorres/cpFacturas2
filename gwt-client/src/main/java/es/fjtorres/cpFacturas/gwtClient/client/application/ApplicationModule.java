package es.fjtorres.cpFacturas.gwtClient.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import es.fjtorres.cpFacturas.gwtClient.client.application.user.UserListPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.user.UserListView;

public class ApplicationModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class,
                ApplicationView.class, ApplicationPresenter.MyProxy.class);

        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class,
                HomePresenter.MyProxy.class);

        bindPresenter(UserListPresenter.class, UserListPresenter.MyView.class, UserListView.class,
                UserListPresenter.MyProxy.class);
    }

}
