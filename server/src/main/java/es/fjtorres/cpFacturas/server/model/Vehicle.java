package es.fjtorres.cpFacturas.server.model;

import java.util.Objects;
import java.util.Set;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;

import es.fjtorres.cpFacturas.common.FuelType;

@Entity
@Table(name = "VEHICLES")
public class Vehicle extends AbstractEntity<Long> {

   private static final long serialVersionUID = 6547753823781206040L;

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_SEQ")
   @SequenceGenerator(name = "VEHICLE_SEQ", sequenceName = "VEHICLE_SEQ", allocationSize = 1)
   private Long id;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
   private Set<Invoice> invoices;

   @NotEmpty(message = "{vehicle.registration.required}")
   @Column(name = "REGISTRATION", length = 25, nullable = false, unique = true)
   private String registration;

   @NotNull(message = "{vehicle.customer.required}")
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CUSTOMER_ID", nullable = false)
   private Customer customer;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "INSURER_ID")
   private Insurer insurer;
   
   @NotNull(message = "{vehicle.model.required}")
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "MODEL_ID", nullable = false)
   private VehicleModel model;

   @NotNull(message = "{vehicle.fuel.required}")
   @Enumerated(EnumType.STRING)
   @Column(name = "FUEL_TYPE", length = 25, nullable = false)
   private FuelType fuelType;

   @NotNull(message = "{vehicle.year.required}")
   @Column(name = "YEAR", nullable = false)
   private Integer year;

   @NotNull(message = "{vehicle.doors.required}")
   @Column(name = "DOORS", nullable = false)
   private Short doors;

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
      return MoreObjects.toStringHelper(this).add("Registration", getRegistration()).toString();
   }

   public VehicleModel getModel() {
      return model;
   }

   public void setModel(VehicleModel pModel) {
      model = pModel;
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

   public Insurer getInsurer() {
      return insurer;
   }

   public void setInsurer(Insurer pInsurer) {
      insurer = pInsurer;
   }

}
