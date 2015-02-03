package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/authentication")
public interface IAuthenticationRpc extends RemoteService {

    boolean login(String username, String password);

    void logout();
}
