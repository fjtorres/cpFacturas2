package es.fjtorres.cpFacturas.server.service.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.service.persistence.criteria.Join;

public class CustomQueryBuilder<T> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CustomQueryBuilder.class);
   
   private final EntityManager entityManager;
   private final Class<T> persistenceClass;
   private CriteriaBuilder builder;
   private CriteriaQuery<T> query;
   private Root<T> select;
   private List<Predicate> where;
   private List<Order> orders;

   public CustomQueryBuilder(final EntityManager pEntityManager, final Class<T> pPersistenceClass) {
      this.entityManager = pEntityManager;
      this.persistenceClass = pPersistenceClass;

      init();
   }

   public EntityManager getEntityManager() {
      return entityManager;
   }

   public Class<T> getPersistenceClass() {
      return persistenceClass;
   }

   private void init() {
      builder = getEntityManager().getCriteriaBuilder();
      query = builder.createQuery(getPersistenceClass());

      select = query.from(getPersistenceClass());
      query.select(select);

      this.where = new ArrayList<Predicate>();
      this.orders = new ArrayList<Order>();
   }

   public boolean isField(final String field) {
      boolean exist = true;
      try {
         select.get(field);
      } catch (final IllegalArgumentException iae) {
         LOGGER.warn("Invalid field \"" + field + "\" for root:" + select);
         exist = false;
      }
      return exist;
   }

   public TypedQuery<T> build() {
      if (where != null && !where.isEmpty()) {
         query.where(where.toArray(new Predicate[] {}));
      }

      if (orders != null && !orders.isEmpty()) {
         query.orderBy(orders);
      }

      return getEntityManager().createQuery(query);
   }

   public CustomQueryBuilder<T> equal(final String field, final Object value) {
      if (isField(field)) {
         where.add(builder.equal(select.get(field), value));
      }

      return this;
   }

   public CustomQueryBuilder<T> notEqual(final String field, final Object value) {
      if (isField(field)) {
         where.add(builder.notEqual(select.get(field), value));
      }

      return this;
   }

   public CustomQueryBuilder<T> ge(final String field, final Number value) {
      if (isField(field) && value != null) {
         where.add(builder.ge(select.get(field), value));
      }

      return this;
   }

   public <V extends Comparable<? super V>> CustomQueryBuilder<T> greaterThan(final String field,
         final V value) {
      if (isField(field) && value != null) {
         where.add(builder.greaterThan(select.get(field), value));
      }

      return this;
   }

   public CustomQueryBuilder<T> le(final String field, final Number value) {
      if (isField(field) && value != null) {
         where.add(builder.le(select.get(field), value));
      }

      return this;
   }

   public <V extends Comparable<? super V>> CustomQueryBuilder<T> lessThan(final String field,
         final V value) {
      if (isField(field) && value != null) {
         where.add(builder.lessThan(select.get(field), value));
      }

      return this;
   }

   public CustomQueryBuilder<T> like(final String field, final String value) {
      if (isField(field) && StringUtils.isNotBlank(value)) {
         where.add(builder.like(select.get(field), "%" + value + "%"));
      }

      return this;
   }

   public CustomQueryBuilder<T> likeLeft(final String field, final String value) {
      if (isField(field) && StringUtils.isNotBlank(value)) {
         where.add(builder.like(builder.lower(select.get(field)), "%" + value));
      }

      return this;
   }

   public CustomQueryBuilder<T> likeRight(final String field, final String value) {
      if (isField(field) && StringUtils.isNotBlank(value)) {
         where.add(builder.like(builder.lower(select.get(field)), value + "%"));
      }

      return this;
   }

   public CustomQueryBuilder<T> likeIgnoreCase(final String field, final String value) {
      if (isField(field) && StringUtils.isNotBlank(value)) {
         where.add(builder.like(builder.lower(select.get(field)), "%" + value.toLowerCase() + "%"));
      }

      return this;
   }

   public CustomQueryBuilder<T> likeLeftIgnoreCase(final String field, final String value) {
      if (isField(field) && StringUtils.isNotBlank(value)) {
         where.add(builder.like(builder.lower(select.get(field)), "%" + value.toLowerCase()));
      }

      return this;
   }

   public CustomQueryBuilder<T> likeRightIgnoreCase(final String field, final String value) {
      if (isField(field) && StringUtils.isNotBlank(value)) {
         where.add(builder.like(builder.lower(select.get(field)), value.toLowerCase() + "%"));
      }

      return this;
   }

   public CustomQueryBuilder<T> order(final String sortField, final OrderBy sortDirection) {
      if (sortDirection != null && StringUtils.isNotBlank(sortField) && isField(sortField)) {
         final Path<Object> field = select.get(sortField);
         switch (sortDirection) {
         case ASC:
            orders.add(builder.asc(field));
            break;
         case DESC:
            orders.add(builder.desc(field));
            break;
         }
      }
      return this;
   }

   public CriteriaBuilder getCriteriaBuilder() {
      return builder;
   }

   public void join(Join pJoin) {
      if (pJoin != null && isField(pJoin.getJoinField())) {
         select.join(pJoin.getJoinField());
      }
   }
}
