package es.fjtorres.cpFacturas.gwtClient.client.security;

import es.fjtorres.cpFacturas.common.dto.UserDto;

public class CurrentUser {

    private boolean loggedIn;
    private UserDto user;

    public void reset () {
        loggedIn = false;
        setUser(null);
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean pLoggedIn) {
        loggedIn = pLoggedIn;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto pUser) {
        user = pUser;
    }
}
