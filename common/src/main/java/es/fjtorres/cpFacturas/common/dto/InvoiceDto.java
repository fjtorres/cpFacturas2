package es.fjtorres.cpFacturas.common.dto;

import java.math.BigDecimal;
import java.util.Set;

import es.fjtorres.cpFacturas.common.InvoiceState;

public class InvoiceDto extends AbstractDto<Long> {

   private static final long serialVersionUID = 1989310313045364430L;

   private Long id;

   private InvoiceState state = InvoiceState.CREATED;

   private VehicleDto vehicle;

   private Set<InvoiceLineDto> lines;

   public BigDecimal getTotalWithTax() {
      BigDecimal total = BigDecimal.ZERO;
      if (lines != null && !lines.isEmpty()) {
         for (final InvoiceLineDto line : lines) {
            if (line != null) {
               total = total.add(line.getTotalWithTaxRate());
            }
         }
      }
      return total;
   }

   public BigDecimal getTotalWithoutTax() {
      BigDecimal total = BigDecimal.ZERO;
      if (lines != null && !lines.isEmpty()) {
         for (final InvoiceLineDto line : lines) {
            if (line != null) {
               total = total.add(line.getTotalWithoutTaxRate());
            }
         }
      }
      return total;
   }

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

   public Set<InvoiceLineDto> getLines() {
      return lines;
   }

   public void setLines(Set<InvoiceLineDto> pLines) {
      lines = pLines;
   }
}
