package es.fjtorres.cpFacturas.server.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity<Long> implements UserDetails {

   /**
    * 
    */
   private static final long serialVersionUID = 6045199015630759401L;

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
   @SequenceGenerator(
         name = "USER_SEQ",
         sequenceName = "USER_SEQ",
         allocationSize = 1)
   private Long id;

   @Column(name = "USERNAME", length = 100, nullable = false, unique = true)
   private String username;

   @Column(name = "PASSWORD", length = 100, nullable = false)
   private String password;

   @Column(name = "ENABLED")
   private boolean enabled;

   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name = "AUTHORITIES", joinColumns = {
      @JoinColumn(name = "USER_ID")
   })
   @Column(name = "AUTHORITY", nullable = false)
   private Set<String> authorities;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

      if (authorities != null && !authorities.isEmpty()) {
         for (String authority : authorities) {
            if (authority != null && authority.trim().length() != 0) {
               grantedAuthorities.add(new SimpleGrantedAuthority(authority
                     .trim()));
            }
         }
      }

      return grantedAuthorities;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return enabled;
   }

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof User) {
         final User other = (User) pObj;
         isEquals = Objects.equals(getUsername(), other.getUsername());
      }
      return isEquals;
   }

   @Override
   public int hashCode() {
      return Objects.hash(getUsername());
   }

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(final Long id) {
      this.id = id;
   }

   @Override
   public String getUsername() {
      return username;
   }

   public void setUsername(final String username) {
      this.username = username;
   }

   @Override
   public String getPassword() {
      return password;
   }

   public void setPassword(final String password) {
      this.password = password;
   }

   public void setEnabled(final boolean enabled) {
      this.enabled = enabled;
   }

   public void setAuthorities(final Set<String> authorities) {
      this.authorities = authorities;
   }

}
