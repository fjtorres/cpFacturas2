package es.fjtorres.cpFacturas.server.service.persistence.criteria;

import java.io.Serializable;

public class Join implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -401803068276143135L;
   
   private String joinField;

   public Join() {

   }

   /**
    * @param pJoinField
    */
   public Join(final String pJoinField) {
      joinField = pJoinField;
   }

   public String getJoinField() {
      return joinField;
   }

   public void setJoinField(String pJoinField) {
      joinField = pJoinField;
   }

}
