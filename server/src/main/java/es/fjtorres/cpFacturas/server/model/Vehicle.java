package es.fjtorres.cpFacturas.server.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "VEHICLES")
public class Vehicle extends AbstractEntity<Long> {

   private static final long serialVersionUID = 6547753823781206040L;

   @Id
   @GeneratedValue(
         strategy = GenerationType.SEQUENCE,
         generator = "VEHICLE_SEQ")
   @SequenceGenerator(
         name = "VEHICLE_SEQ",
         sequenceName = "VEHICLE_SEQ",
         allocationSize = 1)
   private Long id;

   @Column(name = "REGISTRATION", length = 25, nullable = false, unique = true)
   private String registration;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CUSTOMER_ID", nullable = false)
   private Customer customer;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "MODEL_ID", nullable = false)
   private VehicleModel model;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
   private Set<Invoice> invoices;

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof Vehicle) {
         final Vehicle other = (Vehicle) pObj;
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

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(final Customer pCustomer) {
      customer = pCustomer;
   }

   public String getRegistration() {
      return registration;
   }

   public void setRegistration(final String pRegistration) {
      registration = pRegistration;
   }

   public Set<Invoice> getInvoices() {
      return invoices;
   }

   public void setInvoices(final Set<Invoice> pInvoices) {
      invoices = pInvoices;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("Registration",
            getRegistration()).toString();
   }
}
