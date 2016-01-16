package es.fjtorres.cpFacturas.server.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import es.fjtorres.cpFacturas.common.InvoiceState;

@Entity
@Table(name = "INVOICES")
public class Invoice extends AbstractEntity<Long> {

    private static final long serialVersionUID = 2190612963674804427L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_SEQ")
    @SequenceGenerator(name = "INVOICE_SEQ", sequenceName = "INVOICE_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "OBSERVATIONS", length = 500)
    private String observations;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false, length = 25)
    private InvoiceState state = InvoiceState.CREATED;
    
    @NotNull
    @DecimalMin(value="0.00")
    @DecimalMax(value="100.00")
    @Column(name = "TAX_RATE", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_ID", nullable = false)
    private Vehicle vehicle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceLine> lines;

    public boolean isNew () {
       return getId() == null || DEFAULT_ID.equals(getId());
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> pLines) {
        lines = pLines;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle pVehicle) {
        vehicle = pVehicle;
    }

   public String getObservations() {
      return observations;
   }

   public void setObservations(String observations) {
      this.observations = observations;
   }

   public InvoiceState getState() {
      return state;
   }

   public void setState(InvoiceState state) {
      this.state = state;
   }

   public BigDecimal getTaxRate() {
      return taxRate;
   }

   public void setTaxRate(BigDecimal taxRate) {
      this.taxRate = taxRate;
   }
}
