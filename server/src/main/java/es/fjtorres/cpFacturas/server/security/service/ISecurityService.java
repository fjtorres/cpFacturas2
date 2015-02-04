package es.fjtorres.cpFacturas.server.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import es.fjtorres.cpFacturas.common.dto.UserDto;

public interface ISecurityService {

   String login(String username, String password);

   void logout();

   UserDto getLoggedUser();

   boolean validateToken(final String authToken, final UserDetails userDetails);

   String getUserNameFromToken(final String authToken);
}
