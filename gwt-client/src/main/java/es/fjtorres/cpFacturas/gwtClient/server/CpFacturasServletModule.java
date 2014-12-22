package es.fjtorres.cpFacturas.gwtClient.server;

import javax.inject.Singleton;

import com.google.inject.servlet.ServletModule;

import es.fjtorres.cpFacturas.gwtClient.server.rpc.impl.CustomerRpcImpl;

public class CpFacturasServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(CustomerRpcImpl.class).in(Singleton.class);
        serve("/cpFacturas/services/customer").with(CustomerRpcImpl.class);
    }

}
