package es.fjtorres.cpFacturas.server.security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PasswordEncoderTest {

   private PasswordEncoder encoder;

   @BeforeMethod
   public void before() {
      encoder = new BCryptPasswordEncoder(15);
   }

   @Test
   public void testEncoder() {
      System.out.println("admin: " + encoder.encode("admin"));
      System.out.println("user1: " + encoder.encode("user1"));
   }
}
