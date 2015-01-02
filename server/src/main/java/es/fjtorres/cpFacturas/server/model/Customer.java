package es.fjtorres.cpFacturas.server.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

import es.fjtorres.cpFacturas.common.CustomerType;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractEntity<Long> {

    private static final long serialVersionUID = -854986097302149903L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", length = 25, nullable = false)
    private CustomerType type = CustomerType.PERSON;

    @Column(name = "CODE", length = 100, nullable = false, unique = true)
    private String code;

    // If type is "company" this field store the "company name"
    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    // Only required when type is "PARTICULAR".
    @Column(name = "LAST_NAME", length = 100)
    private String lastName;

    @Embedded
    private ContactData contactData;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Vehicle> vehicles;

    @Override
    public boolean equals(Object pObj) {
        boolean isEquals = false;

        if (this == pObj) {
            isEquals = true;
        } else if (pObj == null) {
            isEquals = false;
        } else if (pObj instanceof Customer) {
            final Customer other = (Customer) pObj;
            isEquals = Objects.equals(getCode(), other.getCode());
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Code", getCode()).toString();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long pId) {
        this.id = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String pCode) {
        code = pCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pFirstName) {
        firstName = pFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String pLastName) {
        lastName = pLastName;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> pVehicles) {
        vehicles = pVehicles;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType pType) {
        type = pType;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData pContactData) {
        contactData = pContactData;
    }

}
