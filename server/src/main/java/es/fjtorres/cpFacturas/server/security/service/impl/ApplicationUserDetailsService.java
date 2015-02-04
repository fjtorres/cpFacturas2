package es.fjtorres.cpFacturas.server.security.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import es.fjtorres.cpFacturas.server.model.User;
import es.fjtorres.cpFacturas.server.service.IPersistenceService;

@Named("appUserDetailsService")
public class ApplicationUserDetailsService implements UserDetailsService {

   private final IPersistenceService<Long, User> persistenceService;

   @Inject
   public ApplicationUserDetailsService(
         final IPersistenceService<Long, User> pPersistenceService) {
      this.persistenceService = pPersistenceService;
   }

   @Override
   public UserDetails loadUserByUsername(final String username)
         throws UsernameNotFoundException {
      return persistenceService.findByUniqueField("username", username,
            User.class);
   }

}
