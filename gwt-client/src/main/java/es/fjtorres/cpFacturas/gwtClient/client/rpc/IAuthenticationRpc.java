package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.fjtorres.cpFacturas.common.dto.UserDto;

@RemoteServiceRelativePath("services/authentication")
public interface IAuthenticationRpc extends RemoteService {

    String login(String username, String password);

    void logout();
    
    boolean isLoggedUser();
    
    UserDto getLoggedUser();
}
