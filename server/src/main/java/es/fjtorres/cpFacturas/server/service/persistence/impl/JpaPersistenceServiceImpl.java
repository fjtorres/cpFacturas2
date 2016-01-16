package es.fjtorres.cpFacturas.server.service.persistence.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.model.AbstractEntity;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;

@Named("jpaPersistenceService")
public class JpaPersistenceServiceImpl<Id extends Serializable, T extends AbstractEntity<Id>>
      implements IPersistenceService<Id, T> {

   private static final String ERRO_QUERY_NULL = "Query cannon't be null.";
   private static final String ERROR_PERSISTENT_CONTEXT_NULL = "persistent context cannon't be null";
   private static final String ERROR_PERSISTENT_CLASS_NULL = "Persistence class cannon't be null";
   private static final String ERROR_ENTITY_ID_NULL = "Entity identifier cannon't be null";
   private static final String ERROR_ENTITY_NULL = "Entity cannon't be null";
   
   private static final Logger LOGGER = LoggerFactory.getLogger(JpaPersistenceServiceImpl.class);

   private final PersistenceContextWrapper contextWrapper;

   @Inject
   public JpaPersistenceServiceImpl(final PersistenceContextWrapper pContextWrapper) {
      this.contextWrapper = pContextWrapper;

      if (contextWrapper == null || contextWrapper.getEntityManager() == null) {
         throw new IllegalArgumentException(ERROR_PERSISTENT_CONTEXT_NULL);
      }
   }

   public EntityManager getEntityManager() {
      return contextWrapper.getEntityManager();
   }

   private void checkEntity(final T pEntity) throws NullPointerException {
      Objects.requireNonNull(pEntity, ERROR_ENTITY_NULL);
   }

   private void checkEntityAndIdentifier(final T pEntity) throws NullPointerException {
      checkEntity(pEntity);
      Objects.requireNonNull(pEntity.getId(), ERROR_ENTITY_ID_NULL);
   }

   public boolean isField(final Root<T> from, final String field) {
      boolean exist = true;
      try {
         from.get(field);
      } catch (final IllegalArgumentException iae) {
         LOGGER.warn("Invalid field \"" + field + "\" for root:" + from);
         exist = false;
      }
      return exist;
   }

   @Override
   public void persist(final T pEntity) {
      checkEntity(pEntity);
      getEntityManager().persist(pEntity);
   }

   @Override
   public T update(final T pEntity) throws NullPointerException {
      checkEntityAndIdentifier(pEntity);
      return getEntityManager().merge(pEntity);
   }

   @Override
   public void delete(final T pEntity) throws NullPointerException {
      checkEntityAndIdentifier(pEntity);
      getEntityManager().remove(pEntity);
   }

   @Override
   public void delete(final Id pId, final Class<T> pPersistenceClass) throws NullPointerException {
      Objects.requireNonNull(pId, ERROR_ENTITY_ID_NULL);
      Objects.requireNonNull(pPersistenceClass, ERROR_PERSISTENT_CLASS_NULL);
      getEntityManager().remove(getEntityManager().find(pPersistenceClass, pId));
   }

   @Override
   public T findById(final Id pId, final Class<T> pPersistenceClass) throws NullPointerException {
      Objects.requireNonNull(pId, ERROR_ENTITY_ID_NULL);
      Objects.requireNonNull(pPersistenceClass, ERROR_PERSISTENT_CLASS_NULL);
      return getEntityManager().find(pPersistenceClass, pId);
   }

   @Override
   public List<T> find(final int startPosition, final int maxResults, final String sortField,
         final OrderBy sortDirection, final Class<T> pPersistenceClass) {
      Objects.requireNonNull(pPersistenceClass, ERROR_PERSISTENT_CLASS_NULL);

      final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<T> query = builder.createQuery(pPersistenceClass);
      final Root<T> from = query.from(pPersistenceClass);
      query.select(from);

      if (sortDirection != null && StringUtils.isNotBlank(sortField) && isField(from, sortField)) {
         final Path<Object> field = from.get(sortField);
         switch (sortDirection) {
         case ASC:
            query.orderBy(builder.asc(field));
            break;
         case DESC:
            query.orderBy(builder.desc(field));
            break;
         }
      }

      return getEntityManager().createQuery(query).setFirstResult(startPosition)
            .setMaxResults(maxResults).getResultList();
   }

   @Override
   public Long count(final Class<T> pPersistenceClass) {
      Objects.requireNonNull(pPersistenceClass, ERROR_PERSISTENT_CLASS_NULL);
      // FIXME Long.class is incorrect, Id parameter type
      final CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<Long> query = qb.createQuery(Long.class);
      query.select(qb.count(query.from(pPersistenceClass)));
      return getEntityManager().createQuery(query).getSingleResult();
   }

   @Override
   public <V> T findByUniqueField(final String field, final V fieldValue,
         final Class<T> pPersistenceClass) {
      Objects.requireNonNull(pPersistenceClass, ERROR_PERSISTENT_CLASS_NULL);

      final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<T> query = builder.createQuery(pPersistenceClass);
      final Root<T> from = query.from(pPersistenceClass);
      query.select(from);

      if (isField(from, field)) {
         query.where(builder.equal(from.get(field), fieldValue));
      }

      return getEntityManager().createQuery(query).getSingleResult();
   }

   public List<T> findByFilter(final Map<String, Object> pFilters, final Class<T> pPersistenceClass) {
      Objects.requireNonNull(pPersistenceClass, ERROR_PERSISTENT_CLASS_NULL);

      final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      final CriteriaQuery<T> query = builder.createQuery(pPersistenceClass);
      final Root<T> from = query.from(pPersistenceClass);
      query.select(from);

      if (pFilters != null && !pFilters.isEmpty()) {
         pFilters.entrySet().stream().map(e -> {
            if (isField(from, e.getKey())) {
               query.where(builder.equal(from.get(e.getKey()), e.getValue()));
            }
            return e;
         });
      }

      return getEntityManager().createQuery(query).getResultList();
   }

   @Override
   public <V> List<T> findBySingleFilter(String pFilterField, V pFilterValue,
         Class<T> pPersistenceClass) {
      final Map<String, Object> filterMap = new HashMap<String, Object>();
      filterMap.put(pFilterField, pFilterValue);
      return findByFilter(filterMap, pPersistenceClass);
   }

   @Override
   public List<T> findByQuery(final CriteriaQuery<T> pQuery) {
      Objects.requireNonNull(pQuery, ERRO_QUERY_NULL);
      return getEntityManager().createQuery(pQuery).getResultList();
   }

   @Override
   public T findSingleByQuery(CriteriaQuery<T> pQuery) {
      Objects.requireNonNull(pQuery, ERRO_QUERY_NULL);
      return getEntityManager().createQuery(pQuery).getSingleResult();
   }
}