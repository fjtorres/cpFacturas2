package es.fjtorres.cpFacturas.gwtClient.client.place;

public final class NameTokens {

   public static final String CONFIGURATION = "/configuration";
   public static final String CUSTOMERS = "/customers";
   public static final String CUSTOMERS_EDIT = "/customers/edit/{code}";
   public static final String CUSTOMERS_NEW = "/customers/edit";
   public static final String HOME = "/home";
   public static final String INSURERS = "/insurers";
   public static final String INVOICES = "/invoices";
   public static final String USERS = "/users";
   public static final String VEHICLES = "/vehicles";

   public static String getCustomers() {
      return CUSTOMERS;
   }

   public static String getCustomersEdit() {
      return CUSTOMERS_EDIT;
   }

   public static String getCustomersNew() {
      return CUSTOMERS_NEW;
   }

   public static String getHome() {
      return HOME;
   }

   public static String getInsurers() {
      return INSURERS;
   }

   public static String getInvoices() {
      return INVOICES;
   }

   public static String getUsers() {
      return USERS;
   }

   public static String getVehicles() {
      return VEHICLES;
   }

   public static String getConfiguration() {
      return CONFIGURATION;
   }

}
