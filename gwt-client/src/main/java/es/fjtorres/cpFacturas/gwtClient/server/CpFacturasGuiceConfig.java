package es.fjtorres.cpFacturas.gwtClient.server;

import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

@WebListener
public class CpFacturasGuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        // TODO Auto-generated method stub
        return Guice.createInjector(new CpFacturasServletModule());
    }

}
