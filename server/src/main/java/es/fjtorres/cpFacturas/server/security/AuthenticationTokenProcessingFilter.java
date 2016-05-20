package es.fjtorres.cpFacturas.server.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import es.fjtorres.cpFacturas.server.security.service.ISecurityTokenService;

@Named("authenticationTokenProcessingFilter")
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

   private final UserDetailsService userService;
   private final ISecurityTokenService securityTokenService;

   @Inject
   public AuthenticationTokenProcessingFilter(
         @Named("appUserDetailsService") final UserDetailsService userService,
         final ISecurityTokenService pSecurityTokenService) {
      this.userService = userService;
      this.securityTokenService = pSecurityTokenService;
   }

   @Override
   public void doFilter(final ServletRequest request,
         final ServletResponse response, final FilterChain chain)
         throws IOException, ServletException {
      HttpServletRequest httpRequest = this.getAsHttpRequest(request);

      String authToken = this.extractAuthTokenFromRequest(httpRequest);
      String userName = securityTokenService.getUserNameFromToken(authToken);

      if (userName != null) {

         UserDetails userDetails = this.userService
               .loadUserByUsername(userName);

         if (securityTokenService.validateToken(authToken, userDetails)) {

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource()
                  .buildDetails(httpRequest));
            SecurityContextHolder.getContext()
                  .setAuthentication(authentication);
         }
      }

      chain.doFilter(request, response);
   }

   private HttpServletRequest getAsHttpRequest(final ServletRequest request) {
      if (!(request instanceof HttpServletRequest)) {
         throw new RuntimeException("Expecting an HTTP request");
      }

      return (HttpServletRequest) request;
   }

   private String extractAuthTokenFromRequest(
         final HttpServletRequest httpRequest) {
      /* Get token from header */
      String authToken = httpRequest.getHeader("X-Auth-Token");

      /* If token not found get it from request parameter */
      if (authToken == null) {
         authToken = httpRequest.getParameter("token");
      }

      return authToken;
   }

}
