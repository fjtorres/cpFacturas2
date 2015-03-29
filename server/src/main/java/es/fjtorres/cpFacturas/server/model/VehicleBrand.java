package es.fjtorres.cpFacturas.server.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_BRANDS")
public class VehicleBrand extends AbstractEntity<Long> {

   /**
    * 
    */
   private static final long serialVersionUID = -2222433810775262575L;

   @Id
   @GeneratedValue(
         strategy = GenerationType.SEQUENCE,
         generator = "VEHICLE_BRAND_SEQ")
   @SequenceGenerator(
         name = "VEHICLE_BRAND_SEQ",
         sequenceName = "VEHICLE_BRAND_SEQ",
         allocationSize = 1)
   private Long id;

   @Column(name = "NAME", length = 100, nullable = false, unique = true)
   private String name;
   
   @Column(name = "IS_PREFERENT", nullable = false)
   private boolean preferent;

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof VehicleBrand) {
         final VehicleBrand other = (VehicleBrand) pObj;
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
