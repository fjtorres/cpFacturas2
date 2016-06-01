package es.fjtorres.cpFacturas.server;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import es.fjtorres.cpFacturas.server.security.AuthenticationTokenProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   
   @Inject
   private UserDetailsService userDetailsService;
   
   @Inject
   private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;
   
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.addFilterBefore(authenticationTokenProcessingFilter, AnonymousAuthenticationFilter.class);
      http.authorizeRequests()
         .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
         .antMatchers(HttpMethod.POST, "/api/authentication").permitAll()
         .antMatchers(HttpMethod.GET, "/api/authentication").permitAll()
         .antMatchers("/**").authenticated();
      
      http.csrf().disable();
   }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(authProvider());
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/resources/**", "/js/**", "/static/**");
   }
   
   @Bean
   public DaoAuthenticationProvider authProvider() {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService);
       authProvider.setPasswordEncoder(passwordEncoder());
       return authProvider;
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   
}
