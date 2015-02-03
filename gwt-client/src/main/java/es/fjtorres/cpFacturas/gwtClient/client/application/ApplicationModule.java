package es.fjtorres.cpFacturas.gwtClient.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import es.fjtorres.cpFacturas.gwtClient.client.application.customer.CustomerFormPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.CustomerFormView;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.CustomerListPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.CustomerListView;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MenuPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MenuView;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MessagesPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MessagesView;
import es.fjtorres.cpFacturas.gwtClient.client.application.home.HomePresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.home.HomeView;
import es.fjtorres.cpFacturas.gwtClient.client.application.insurer.InsurerListPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.insurer.InsurerListView;
import es.fjtorres.cpFacturas.gwtClient.client.application.invoice.InvoiceListPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.invoice.InvoiceListView;
import es.fjtorres.cpFacturas.gwtClient.client.application.login.LoginPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.login.LoginView;
import es.fjtorres.cpFacturas.gwtClient.client.application.user.UserListPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.user.UserListView;
import es.fjtorres.cpFacturas.gwtClient.client.application.vehicle.VehicleListPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.vehicle.VehicleListView;

public class ApplicationModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        // Main layout
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class,
                ApplicationView.class, ApplicationPresenter.MyProxy.class);

        // Generic presenters
        bindSingletonPresenterWidget(MessagesPresenter.class, MessagesPresenter.MyView.class,
                MessagesView.class);
        bindSingletonPresenterWidget(MenuPresenter.class, MenuPresenter.MyView.class,
                MenuView.class);
        
        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class,
                HomePresenter.MyProxy.class);
        
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class,
                LoginPresenter.MyProxy.class);

        // FIXME
        bindPresenter(UserListPresenter.class, UserListPresenter.MyView.class, UserListView.class,
                UserListPresenter.MyProxy.class);

        // Customers
        bindPresenter(CustomerListPresenter.class, CustomerListPresenter.MyView.class,
                CustomerListView.class, CustomerListPresenter.MyProxy.class);
        bindPresenter(CustomerFormPresenter.class, CustomerFormPresenter.MyView.class,
                CustomerFormView.class, CustomerFormPresenter.MyProxy.class);
        
        // Insurers
        bindPresenter(InsurerListPresenter.class, InsurerListPresenter.MyView.class,
                InsurerListView.class, InsurerListPresenter.MyProxy.class);
        
        // Invoices
        bindPresenter(InvoiceListPresenter.class, InvoiceListPresenter.MyView.class,
                InvoiceListView.class, InvoiceListPresenter.MyProxy.class);
        
        // Vehicles
        bindPresenter(VehicleListPresenter.class, VehicleListPresenter.MyView.class,
                VehicleListView.class, VehicleListPresenter.MyProxy.class);

    }

}
