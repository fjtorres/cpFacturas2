package es.fjtorres.cpFacturas.common.dto;

import java.util.Objects;
import java.util.Set;

public class UserDto extends AbstractDto<Long> {

   /**
    * 
    */
   private static final long serialVersionUID = -3858276889742822154L;

   private Long id;

   private String username;

   private Set<String> authorities;

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof UserDto) {
         final UserDto other = (UserDto) pObj;
         isEquals = Objects.equals(getUsername(), other.getUsername());
      }
      return isEquals;
   }

   @Override
   public int hashCode() {
      return Objects.hash(getUsername());
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(final String username) {
      this.username = username;
   }

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(final Long id) {
      this.id = id;
   }

   public Set<String> getAuthorities() {
      return authorities;
   }

   public void setAuthorities(final Set<String> authorities) {
      this.authorities = authorities;
   }
}
