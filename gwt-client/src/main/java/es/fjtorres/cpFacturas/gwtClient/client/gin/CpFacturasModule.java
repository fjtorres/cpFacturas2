package es.fjtorres.cpFacturas.gwtClient.client.gin;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

import es.fjtorres.cpFacturas.gwtClient.client.application.ApplicationModule;
import es.fjtorres.cpFacturas.gwtClient.client.place.NameTokens;
import es.fjtorres.cpFacturas.gwtClient.client.security.SecurityModule;

public class CpFacturasModule extends AbstractPresenterModule {

   @Override
   protected void configure() {
      install(new DefaultModule.Builder().placeManager(
            DefaultPlaceManager.class)
            .tokenFormatter(RouteTokenFormatter.class).build());
      install(new SecurityModule());
      install(new ApplicationModule());

      bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.LOGIN);
      bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
      bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.UNAUTHORIZED);

   }

}
