package es.fjtorres.cpFacturas.common.dto;

import java.math.BigDecimal;
import java.util.List;

import es.fjtorres.cpFacturas.common.InvoiceState;

public class InvoiceDto extends AbstractDto<Long> {

   private static final long serialVersionUID = 1989310313045364430L;

   private Long id;

   private String observations;
   
   private InvoiceState state = InvoiceState.CREATED;
   
   private BigDecimal taxRate;

   private VehicleDto vehicle;

   private List<InvoiceLineDto> lines;

   public Long getId() {
      return id;
   }

   public void setId(Long pId) {
      id = pId;
   }

   public InvoiceState getState() {
      return state;
   }

   public void setState(InvoiceState pState) {
      state = pState;
   }

   public VehicleDto getVehicle() {
      return vehicle;
   }

   public void setVehicle(VehicleDto pVehicle) {
      vehicle = pVehicle;
   }

   public List<InvoiceLineDto> getLines() {
      return lines;
   }

   public void setLines(List<InvoiceLineDto> pLines) {
      lines = pLines;
   }

   public String getObservations() {
      return observations;
   }

   public void setObservations(String observations) {
      this.observations = observations;
   }
   
   public BigDecimal getTaxRate() {
      return taxRate;
   }

   public void setTaxRate(BigDecimal pTaxRate) {
      taxRate = pTaxRate;
   }
}
