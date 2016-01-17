package es.fjtorres.cpFacturas.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class ContactData implements Serializable {

    private static final long serialVersionUID = -4754107930809784781L;

    @NotEmpty(message="{contactData.firstPhone.required}")
    @Column(name = "PHONE_1", length = 25, nullable = false)
    private String firstPhoneNumber;

    @Column(name = "PHONE_2", length = 25)
    private String secondPhoneNumber;
    
    @Column(name = "PHONE_3", length = 25)
    private String thirdPhoneNumber;
    
    @Column(name = "PHONE_4", length = 25)
    private String fourthPhoneNumber;

    @Email(message="{contactData.email.format}")
    @Column(name = "EMAIL", length = 250)
    private String email;

    @Column(name = "FAX_1", length = 25)
    private String firstFax;
    
    @Column(name = "FAX_2", length = 25)
    private String secondFax;

    @Column(name = "ADDRESS", length = 250)
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
    }

   public String getFirstPhoneNumber() {
      return firstPhoneNumber;
   }

   public void setFirstPhoneNumber(String firstPhoneNumber) {
      this.firstPhoneNumber = firstPhoneNumber;
   }

   public String getSecondPhoneNumber() {
      return secondPhoneNumber;
   }

   public void setSecondPhoneNumber(String secondPhoneNumber) {
      this.secondPhoneNumber = secondPhoneNumber;
   }

   public String getThirdPhoneNumber() {
      return thirdPhoneNumber;
   }

   public void setThirdPhoneNumber(String thirdPhoneNumber) {
      this.thirdPhoneNumber = thirdPhoneNumber;
   }

   public String getFourthPhoneNumber() {
      return fourthPhoneNumber;
   }

   public void setFourthPhoneNumber(String fourthPhoneNumber) {
      this.fourthPhoneNumber = fourthPhoneNumber;
   }

   public String getFirstFax() {
      return firstFax;
   }

   public void setFirstFax(String firstFax) {
      this.firstFax = firstFax;
   }

   public String getSecondFax() {
      return secondFax;
   }

   public void setSecondFax(String secondFax) {
      this.secondFax = secondFax;
   }
}
