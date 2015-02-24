package es.fjtorres.cpFacturas.server.security;

import java.io.IOException;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@Named("unauthorizedEntryPoint")
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(final HttpServletRequest request,
         final HttpServletResponse response,
         final AuthenticationException authException) throws IOException,
         ServletException {
      if (!request.getMethod().equals(HttpMethod.OPTIONS)) {
         response
               .sendError(HttpServletResponse.SC_UNAUTHORIZED,
                     "Unauthorized: Authentication token was either missing or invalid.");
      }
   }

}
