package es.fjtorres.cpFacturas.common.dto;

import java.io.Serializable;

public class ContactDataDto implements Serializable {

    private static final long serialVersionUID = -4938321742868643632L;

    private String primaryPhoneNumber;

    private String secundayPhoneNumber;

    private String email;

    private String fax;

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
