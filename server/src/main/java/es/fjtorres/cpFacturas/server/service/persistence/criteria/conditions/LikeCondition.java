package es.fjtorres.cpFacturas.server.service.persistence.criteria.conditions;

public class LikeCondition extends Condition<String> {

   /**
    * 
    */
   private static final long serialVersionUID = 5639085145615039743L;

   private boolean ignoreCase;

   public LikeCondition() {

   }

   public LikeCondition(final String pField, final String pValue) {
      this(pField, pValue, true);
   }

   public LikeCondition(final String pField, final String pValue, final boolean pIgnoreCase) {
      super(pField, pValue);

      setIgnoreCase(pIgnoreCase);
   }

   public boolean isIgnoreCase() {
      return ignoreCase;
   }

   public void setIgnoreCase(boolean pIgnoreCase) {
      ignoreCase = pIgnoreCase;
   }
}
