package es.fjtorres.cpFacturas.server.service.persistence.criteria;

import java.io.Serializable;

import es.fjtorres.cpFacturas.common.pagination.OrderBy;

public class Order implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1303500709411916547L;

   private final String field;

   private final OrderBy direction;

   /**
    * @param pField
    * @param pDirection
    */
   public Order(String pField, OrderBy pDirection) {
      super();
      field = pField;
      direction = pDirection;
   }

   public String getField() {
      return field;
   }

   public OrderBy getDirection() {
      return direction;
   }

}
