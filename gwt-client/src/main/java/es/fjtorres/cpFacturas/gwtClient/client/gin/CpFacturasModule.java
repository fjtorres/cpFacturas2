package es.fjtorres.cpFacturas.gwtClient.client.gin;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationModule;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;

public class CpFacturasModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new ApplicationModule());
        
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.HOME);
        
    }

}
