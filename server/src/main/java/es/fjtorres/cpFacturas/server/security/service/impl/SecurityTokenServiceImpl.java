package es.fjtorres.cpFacturas.server.security.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import es.fjtorres.cpFacturas.server.security.service.ISecurityTokenService;

@Named
public class SecurityTokenServiceImpl implements ISecurityTokenService {

   private static final String MAGIC_KEY = "obfuscate";
   
   @Override
   public String createToken(final UserDetails userDetails) {
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
   
   @Override
   public String getUserNameFromToken(final String authToken) {
      if (null == authToken) {
         return null;
      }

      String[] parts = authToken.split(":");
      return parts[0];
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
}
