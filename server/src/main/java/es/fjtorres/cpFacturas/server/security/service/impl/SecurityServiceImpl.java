package es.fjtorres.cpFacturas.server.security.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import es.fjtorres.cpFacturas.common.dto.UserDto;
import es.fjtorres.cpFacturas.server.model.User;
import es.fjtorres.cpFacturas.server.security.service.ISecurityService;
import es.fjtorres.cpFacturas.server.service.IBasicService;

@Named
public class SecurityServiceImpl implements ISecurityService {

   private static final String MAGIC_KEY = "obfuscate";

   private final AuthenticationManager authenticationManager;
   private final IBasicService basicService;

   @Inject
   public SecurityServiceImpl(
         @Named("authenticationManager") final AuthenticationManager pAuthenticationManager,
         final IBasicService pBasicService) {
      this.authenticationManager = pAuthenticationManager;
      this.basicService = pBasicService;
   }

   @Override
   public String login(final String username, final String password) {
      final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            username, password);
      final Authentication authentication = this.authenticationManager
            .authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return createToken((UserDetails) authentication.getPrincipal());
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

   private String createToken(final UserDetails userDetails) {
      /* Expires in one hour */
      long expires = System.currentTimeMillis() + 1000L * 60 * 60;

      StringBuilder tokenBuilder = new StringBuilder();
      tokenBuilder.append(userDetails.getUsername());
      tokenBuilder.append(":");
      tokenBuilder.append(expires);
      tokenBuilder.append(":");
      tokenBuilder.append(computeSignature(userDetails, expires));

      return tokenBuilder.toString();
   }

   private String computeSignature(final UserDetails userDetails,
         final long expires) {
      StringBuilder signatureBuilder = new StringBuilder();
      signatureBuilder.append(userDetails.getUsername());
      signatureBuilder.append(":");
      signatureBuilder.append(expires);
      signatureBuilder.append(":");
      signatureBuilder.append(userDetails.getPassword());
      signatureBuilder.append(":");
      signatureBuilder.append(MAGIC_KEY);

      MessageDigest digest;
      try {
         digest = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
         throw new IllegalStateException("No MD5 algorithm available!");
      }

      return new String(Hex.encode(digest.digest(signatureBuilder.toString()
            .getBytes())));
   }

   @Override
   public String getUserNameFromToken(final String authToken) {
      if (null == authToken) {
         return null;
      }

      String[] parts = authToken.split(":");
      return parts[0];
   }

   @Override
   public boolean validateToken(final String authToken,
         final UserDetails userDetails) {
      String[] parts = authToken.split(":");
      long expires = Long.parseLong(parts[1]);
      String signature = parts[2];

      if (expires < System.currentTimeMillis()) {
         return false;
      }

      return signature.equals(computeSignature(userDetails, expires));
   }

}
