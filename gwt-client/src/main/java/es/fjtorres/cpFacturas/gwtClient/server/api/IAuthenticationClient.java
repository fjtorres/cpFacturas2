package es.fjtorres.cpFacturas.gwtClient.server.api;

import es.fjtorres.cpFacturas.common.dto.UserDto;
import es.fjtorres.cpFacturas.common.exception.AppException;

public interface IAuthenticationClient extends ISecuredClient {

    String login(String username, String password) throws AppException;

    void logout();
    
    UserDto getLoggedUser();
}
