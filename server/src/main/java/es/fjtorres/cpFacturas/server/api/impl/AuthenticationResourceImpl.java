package es.fjtorres.cpFacturas.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.AUTHENTICATION_PATH;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.rest.api.IAuthenticationResource;
import es.fjtorres.cpFacturas.server.security.service.ISecurityService;

@Named
@Path(AUTHENTICATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResourceImpl implements IAuthenticationResource {

   private final ISecurityService securityService;

   @Inject
   public AuthenticationResourceImpl(final ISecurityService pSecurityService) {
      this.securityService = pSecurityService;
   }

   @POST
   @Override
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Response authenticate(@FormParam("username") final String username,
         @FormParam("password") final String password) {
      final Map<String, String> token = new HashMap<>();
      token.put("token", securityService.login(username, password));
      return Response.ok(token).build();
   }

   @GET
   @Override
   public Response getUser() {
      return Response.ok(securityService.getLoggedUser()).build();
   }

   @Path("/logout")
   @GET
   @Override
   public Response logout() {
      securityService.logout();
      return Response.ok().build();
   }

}
