package es.fjtorres.cpFacturas.common.dto;

public class VehicleDto extends AbstractDto<Long> {

   private static final long serialVersionUID = 5179390791312041743L;

   private Long id;

   private String registration;

   private CustomerDto customer;

   private VehicleModelDto model;

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

}
