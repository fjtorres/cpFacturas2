package es.fjtorres.cpFacturas.gwtClient.client.security;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@DefaultGatekeeper
public class LoggedInGatekeeper implements Gatekeeper {

    private final CurrentUser currentUser;
    
    @Inject
    public LoggedInGatekeeper(final CurrentUser pCurrentUser) {
        this.currentUser = pCurrentUser;
    }

    @Override
    public boolean canReveal() {
        return currentUser.isLoggedIn();
    }

}
