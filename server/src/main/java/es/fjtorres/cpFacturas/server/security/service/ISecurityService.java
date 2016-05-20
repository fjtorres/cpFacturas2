package es.fjtorres.cpFacturas.server.security.service;

import es.fjtorres.cpFacturas.common.dto.UserDto;

public interface ISecurityService {

   String login(String username, String password);

   void logout();

   UserDto getLoggedUser();

}
