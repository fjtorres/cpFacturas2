package es.fjtorres.cpFacturas.server.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface ISecurityTokenService {
   boolean validateToken(final String authToken, final UserDetails userDetails);

   String createToken(final UserDetails userDetails);

   String getUserNameFromToken(String authToken);
}
