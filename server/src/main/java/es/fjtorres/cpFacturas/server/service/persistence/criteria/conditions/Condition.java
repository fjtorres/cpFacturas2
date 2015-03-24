package es.fjtorres.cpFacturas.server.service.persistence.criteria.conditions;

import java.io.Serializable;

public class Condition<V extends Serializable> implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -6573199453316403976L;

   private String alias;
   
   private String field;

   private V value;

   public Condition() {

   }

   public Condition(final String pField, final V pValue) {
      setField(pField);
      setValue(pValue);
   }

   public V getValue() {
      return value;
   }

   public void setValue(V pValue) {
      value = pValue;
   }

   public String getField() {
      return field;
   }

   public void setField(String pField) {
      field = pField;
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String pAlias) {
      alias = pAlias;
   }

}
