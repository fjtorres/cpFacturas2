package es.fjtorres.cpFacturas.common.dto;

import java.io.Serializable;

public class ContactDataDto implements Serializable {

    private static final long serialVersionUID = -4938321742868643632L;

    private String firstPhoneNumber;

    private String secondPhoneNumber;
    
    private String thirdPhoneNumber;
    
    private String fourthPhoneNumber;

    private String email;

    private String firstFax;
    
    private String secondFax;

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
