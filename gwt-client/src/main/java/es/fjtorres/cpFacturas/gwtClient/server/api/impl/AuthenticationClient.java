package es.fjtorres.cpFacturas.gwtClient.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.AUTHENTICATION_PATH;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.AUTHENTICATION_LOGOUT_PATH;

import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import es.fjtorres.cpFacturas.common.dto.UserDto;
import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.gwtClient.server.api.IAuthenticationClient;

public class AuthenticationClient extends AbstractApiClient implements IAuthenticationClient {

    public AuthenticationClient(final String pBaseUrl) {
        super(pBaseUrl);
    }

    @Override
    public String login(final String pUsername, final String pPassword) throws AppException {
        final MultivaluedMap<String, String> params = new MultivaluedHashMap<>();
        params.add("username", pUsername);
        params.add("password", pPassword);

        GenericType<Map<String, String>> type = new GenericType<Map<String, String>>() {
        };
        Map<String, String> result = getTarget(AUTHENTICATION_PATH).request().post(
                parseEntity(params, MediaType.APPLICATION_FORM_URLENCODED), type);

        String token = null;
        if (result != null && result.containsKey("token")) {
            token = result.get("token");
        }
        return token;
    }

    @Override
    public void logout() {
        getTarget(AUTHENTICATION_LOGOUT_PATH).request().header(AUTH_HEADER, getAuthToken()).get();
    }

    @Override
    public UserDto getLoggedUser() {
        return getTarget(AUTHENTICATION_PATH).request().header(AUTH_HEADER, getAuthToken()).get(UserDto.class);
    }

}
