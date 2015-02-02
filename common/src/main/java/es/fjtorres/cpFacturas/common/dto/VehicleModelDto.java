package es.fjtorres.cpFacturas.common.dto;

import java.util.Objects;

public class VehicleModelDto extends AbstractDto<Long> {

   /**
    * 
    */
   private static final long serialVersionUID = 7674497309762466092L;

   private Long id;

   private String name;

   private VehicleBrandDto brand;

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof VehicleModelDto) {
         final VehicleModelDto other = (VehicleModelDto) pObj;
         isEquals = Objects.equals(getName(), other.getName())
               && Objects.equals(getBrand(), other.getBrand());
      }
      return isEquals;
   }

   @Override
   public int hashCode() {
      return Objects.hash(getName(), getBrand());
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

   public VehicleBrandDto getBrand() {
      return brand;
   }

   public void setBrand(final VehicleBrandDto brand) {
      this.brand = brand;
   }
}
