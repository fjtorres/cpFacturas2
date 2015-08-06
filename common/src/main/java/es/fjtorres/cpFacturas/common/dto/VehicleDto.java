package es.fjtorres.cpFacturas.common.dto;

import java.util.Objects;

import es.fjtorres.cpFacturas.common.FuelType;

public class VehicleDto extends AbstractDto<Long> {

   private static final long serialVersionUID = 5179390791312041743L;

   private Long id;

   private String registration;

   private CustomerDto customer;
   
   private InsurerDto insurer;

   private VehicleModelDto model;

   private FuelType fuelType;

   private Integer year;

   private Short doors;

   @Override
   public boolean equals(Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof VehicleDto) {
         final VehicleDto other = (VehicleDto) pObj;
         isEquals = Objects.equals(getRegistration(), other.getRegistration());
      }
      return isEquals;
   }

   @Override
   public int hashCode() {
      return Objects.hash(getRegistration());
   }

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(final Long pId) {
      id = pId;
   }

   public String getRegistration() {
      return registration;
   }

   public void setRegistration(final String pRegistration) {
      registration = pRegistration;
   }

   public CustomerDto getCustomer() {
      return customer;
   }

   public void setCustomer(final CustomerDto pCustomer) {
      customer = pCustomer;
   }

   public VehicleModelDto getModel() {
      return model;
   }

   public void setModel(final VehicleModelDto model) {
      this.model = model;
   }

   public FuelType getFuelType() {
      return fuelType;
   }

   public void setFuelType(FuelType pFuelType) {
      fuelType = pFuelType;
   }

   public Integer getYear() {
      return year;
   }

   public void setYear(Integer pYear) {
      year = pYear;
   }

   public Short getDoors() {
      return doors;
   }

   public void setDoors(Short pDoors) {
      doors = pDoors;
   }

   public InsurerDto getInsurer() {
      return insurer;
   }

   public void setInsurer(InsurerDto pInsurer) {
      insurer = pInsurer;
   }

}
