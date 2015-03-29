package es.fjtorres.cpFacturas.common.dto;

import java.util.Objects;

public class VehicleBrandDto extends AbstractDto<Long> {

   /**
    * 
    */
   private static final long serialVersionUID = 7721390593740519853L;

   private Long id;

   private String name;

   private boolean preferent;

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof VehicleBrandDto) {
         final VehicleBrandDto other = (VehicleBrandDto) pObj;
         isEquals = Objects.equals(getName(), other.getName());
      }
      return isEquals;
   }

   @Override
   public int hashCode() {
      return Objects.hash(getName());
   }

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(final Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public boolean isPreferent() {
      return preferent;
   }

   public void setPreferent(boolean pPreferent) {
      preferent = pPreferent;
   }
}
