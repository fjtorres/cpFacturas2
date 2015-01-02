package es.fjtorres.cpFacturas.common.dto;

import java.util.Objects;
import java.util.Set;

public class CustomerDto extends AbstractDto<Long> {

    private static final long serialVersionUID = 4003447769673727730L;

    private Long id;

    private String code;

    private String firstName;

    private String lastName;

    private ContactDataDto contactData;

    private Set<VehicleDto> vehicles;
    
    public CustomerDto() {
        this.contactData = new ContactDataDto();
    }

    @Override
    public boolean equals(Object pObj) {
        boolean isEquals = false;

        if (this == pObj) {
            isEquals = true;
        } else if (pObj == null) {
            isEquals = false;
        } else if (pObj instanceof CustomerDto) {
            final CustomerDto other = (CustomerDto) pObj;
            isEquals = Objects.equals(getCode(), other.getCode());
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
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

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public ContactDataDto getContactData() {
        return contactData;
    }

    public void setContactData(ContactDataDto pContactData) {
        contactData = pContactData;
    }

    public Set<VehicleDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<VehicleDto> pVehicles) {
        vehicles = pVehicles;
    }

}
