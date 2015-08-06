package es.fjtorres.cpFacturas.server.model;

import java.math.BigDecimal;

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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "INVOICE_LINES")
public class InvoiceLine extends AbstractEntity<Long> {

    private static final long serialVersionUID = -7382153480726165512L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_LINE_SEQ")
    @SequenceGenerator(name = "INVOICE_LINE_SEQ", sequenceName = "INVOICE_LINE_SEQ", allocationSize = 1)
    private Long id;

    @DecimalMin(value="0.00")
    @DecimalMax(value="99999.99")
    @Column(name = "PRICE", nullable = false, precision = 7, scale = 2)
    private BigDecimal price;

    @DecimalMin(value="0.00")
    @DecimalMax(value="99999.99")
    @Column(name = "AMOUNT", nullable = false, precision = 7, scale = 2)
    private BigDecimal amount;

    @DecimalMin(value="0.00")
    @DecimalMax(value="100.00")
    @Column(name = "TAX_RATE", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxRate;

    @DecimalMin(value="0.00")
    @DecimalMax(value="100.00")
    @Column(name = "DISCOUNT", nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;

    @Column(name = "DESCRIPTION", length = 250, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private Invoice invoice;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice pInvoice) {
        invoice = pInvoice;
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

}
