package es.fjtorres.cpFacturas.rest.api;

import javax.ws.rs.core.Response;

public interface IAuthenticationResource {

   Response authenticate(String username, String password);

   Response getUser();

   Response logout();
}
