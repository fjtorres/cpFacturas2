package es.fjtorres.cpFacturas.server.service.persistence.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.fjtorres.cpFacturas.server.service.persistence.criteria.conditions.Condition;

public class SearchInfo {

   private List<Condition<?>> conditions;

   private List<Order> orders;

   private List<Join> joins;

   public <V extends Serializable> void addCondition(final Condition<V> condition) {
      if (conditions == null) {
         conditions = new ArrayList<Condition<?>>();
      }

      conditions.add(condition);
   }

   public void addOrder(final Order order) {
      if (orders == null) {
         orders = new ArrayList<Order>();
      }

      orders.add(order);
   }

   public void addJoin(final Join pJoin) {
      if (joins == null) {
         joins = new ArrayList<Join>();
      }

      joins.add(pJoin);
   }

   public boolean hasConditions() {
      return has(conditions);
   }

   public boolean hasOrders() {
      return has(orders);
   }

   public boolean hasJoins() {
      return has(joins);
   }

   private boolean has(final List<?> pList) {
      return pList != null && !pList.isEmpty();
   }

   public List<Condition<?>> getConditions() {
      return conditions;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public List<Join> getJoins() {
      return joins;
   }
}
