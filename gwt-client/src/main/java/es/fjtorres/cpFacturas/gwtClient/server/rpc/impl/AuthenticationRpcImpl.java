package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.fjtorres.cpFacturas.common.dto.UserDto;
import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.IAuthenticationRpc;
import es.fjtorres.cpFacturas.gwtClient.server.api.IAuthenticationClient;
import es.fjtorres.cpFacturas.gwtClient.server.api.impl.AuthenticationClient;

@Singleton
public class AuthenticationRpcImpl extends AbstractSecuredRpc implements IAuthenticationRpc {

    private static final long serialVersionUID = 208363308572876489L;

    private IAuthenticationClient client = new AuthenticationClient("http://localhost:8080/server");

    @Inject
    public AuthenticationRpcImpl() {

    }

    @Override
    public String login(final String pUsername, final String pPassword) {
        String token = null;
        try {
            token = client.login(pUsername, pPassword);
        } catch (AppException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void logout() {
        client.setAuthToken(getAuthToken());
        client.logout();
    }

    @Override
    public boolean isLoggedUser() {
        String token = getAuthToken();
        boolean logged = false;
        if (token != null && !token.trim().isEmpty() && getLoggedUser() != null) {
            logged = true;
        }
        return logged;
    }

    @Override
    public UserDto getLoggedUser() {
        client.setAuthToken(getAuthToken());
        return client.getLoggedUser();
    }
}
