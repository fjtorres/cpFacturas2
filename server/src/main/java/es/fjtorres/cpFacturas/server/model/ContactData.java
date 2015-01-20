package es.fjtorres.cpFacturas.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class ContactData implements Serializable {

    private static final long serialVersionUID = -4754107930809784781L;

    @NotEmpty(message="{customer.primaryPhone.required}")
    @Column(name = "PHONE_1", length = 25, nullable = false)
    private String primaryPhoneNumber;

    @Column(name = "PHONE_2", length = 25)
    private String secundayPhoneNumber;

    @Email(message="{customer.email.format}")
    @Column(name = "EMAIL", length = 250)
    private String email;

    @Column(name = "FAX", length = 25)
    private String fax;

    @Column(name = "ADDRESS", length = 250)
    private String address;

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String pPrimaryPhoneNumber) {
        primaryPhoneNumber = pPrimaryPhoneNumber;
    }

    public String getSecundayPhoneNumber() {
        return secundayPhoneNumber;
    }

    public void setSecundayPhoneNumber(String pSecundayPhoneNumber) {
        secundayPhoneNumber = pSecundayPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String pFax) {
        fax = pFax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
    }
}
