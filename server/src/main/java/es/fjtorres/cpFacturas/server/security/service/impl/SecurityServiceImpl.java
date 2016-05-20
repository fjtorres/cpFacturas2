package es.fjtorres.cpFacturas.server.security.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import es.fjtorres.cpFacturas.common.dto.UserDto;
import es.fjtorres.cpFacturas.server.model.User;
import es.fjtorres.cpFacturas.server.security.service.ISecurityService;
import es.fjtorres.cpFacturas.server.security.service.ISecurityTokenService;
import es.fjtorres.cpFacturas.server.service.IBasicService;

@Named
public class SecurityServiceImpl implements ISecurityService {

   private final AuthenticationManager authenticationManager;
   private final IBasicService basicService;
   private final ISecurityTokenService securityTokenService;

   @Inject
   public SecurityServiceImpl(
         @Named("authenticationManager") final AuthenticationManager pAuthenticationManager,
         final IBasicService pBasicService, final ISecurityTokenService pSecurityTokenService) {
      this.authenticationManager = pAuthenticationManager;
      this.basicService = pBasicService;
      this.securityTokenService = pSecurityTokenService;
   }

   @Override
   public String login(final String username, final String password) {
      final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            username, password);
      final Authentication authentication = this.authenticationManager
            .authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return securityTokenService.createToken((UserDetails) authentication.getPrincipal());
   }

   @Override
   public void logout() {
      SecurityContextHolder.clearContext();
   }

   @Override
   public UserDto getLoggedUser() {
      Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
      Object principal = authentication.getPrincipal();
      if (principal instanceof String
            && ((String) principal).equals("anonymousUser")) {
         throw new AuthenticationCredentialsNotFoundException("anonymous user");
      }

      final User user = (User) principal;

      return basicService.convert(user, UserDto.class);
   }
}
