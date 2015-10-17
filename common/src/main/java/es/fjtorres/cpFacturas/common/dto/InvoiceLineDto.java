package es.fjtorres.cpFacturas.common.dto;

import java.math.BigDecimal;

public class InvoiceLineDto extends AbstractDto<Long> {

   private static final long serialVersionUID = -5260477311368504991L;

   private Long id;

   private BigDecimal price;

   private BigDecimal amount;

   private BigDecimal taxRate;

   private BigDecimal discount;

   private String description;

//   public BigDecimal getTotalWithTaxRate() {
//      BigDecimal total = BigDecimal.ZERO;
//      if (getPrice() != null && getAmount() != null) {
//         total = getPriceWithTaxRate().multiply(getAmount());
//      }
//      return total;
//   }
//
//   public BigDecimal getTotalWithoutTaxRate() {
//      BigDecimal total = BigDecimal.ZERO;
//      if (getPrice() != null && getAmount() != null) {
//         total = getPrice().multiply(getAmount());
//      }
//      return total;
//   }
//
//   public BigDecimal getTaxRateAmount() {
//      BigDecimal total = BigDecimal.ZERO;
//      if (getPrice() != null && getTaxRate() != null) {
//         total = (getPrice().multiply(getTaxRate())).divide(new BigDecimal(100));
//      }
//      return total;
//   }
//
//   public BigDecimal getPriceWithTaxRate() {
//      BigDecimal priceWithTaxRate = BigDecimal.ZERO;
//      if (getPrice() != null) {
//         priceWithTaxRate = getPrice().add(getTaxRateAmount());
//      }
//      return priceWithTaxRate;
//   }

   public Long getId() {
      return id;
   }

   public void setId(Long pId) {
      id = pId;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal pPrice) {
      price = pPrice;
   }

   public BigDecimal getAmount() {
      return amount;
   }

   public void setAmount(BigDecimal pAmount) {
      amount = pAmount;
   }

   public BigDecimal getTaxRate() {
      return taxRate;
   }

   public void setTaxRate(BigDecimal pTaxRate) {
      taxRate = pTaxRate;
   }

   public BigDecimal getDiscount() {
      return discount;
   }

   public void setDiscount(BigDecimal pDiscount) {
      discount = pDiscount;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String pDescription) {
      description = pDescription;
   }
}
