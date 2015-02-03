package es.fjtorres.cpFacturas.server.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_MODELS")
public class VehicleModel extends AbstractEntity<Long> {

   /**
    * 
    */
   private static final long serialVersionUID = -1636973159009362329L;

   @Id
   @GeneratedValue(
         strategy = GenerationType.SEQUENCE,
         generator = "VEHICLE_MODEL_SEQ")
   @SequenceGenerator(
         name = "VEHICLE_MODEL_SEQ",
         sequenceName = "VEHICLE_MODEL_SEQ",
         allocationSize = 1)
   private Long id;

   @Column(name = "NAME", length = 100, nullable = false)
   private String name;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "BRAND_ID", nullable = false)
   private VehicleBrand brand;

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof VehicleModel) {
         final VehicleModel other = (VehicleModel) pObj;
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

   public VehicleBrand getBrand() {
      return brand;
   }

   public void setBrand(final VehicleBrand brand) {
      this.brand = brand;
   }
}
