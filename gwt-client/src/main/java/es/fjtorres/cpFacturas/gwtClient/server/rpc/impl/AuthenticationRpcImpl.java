package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import es.fjtorres.cpFacturas.gwtClient.client.rpc.IAuthenticationRpc;

@Singleton
public class AuthenticationRpcImpl extends RemoteServiceServlet implements IAuthenticationRpc {

    private static final long serialVersionUID = 208363308572876489L;

    @Inject
    public AuthenticationRpcImpl() {

    }

    @Override
    public boolean login(final String pUsername, final String pPassword) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub

    }
}
