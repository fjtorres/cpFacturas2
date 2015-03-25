package es.fjtorres.cpFacturas.server.service.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.model.AbstractEntity;

public interface IPersistenceService<Id extends Serializable, T extends AbstractEntity<Id>> {

   EntityManager getEntityManager();

   /**
    * Persist entity.
    * 
    * @param entity
    *           Entity instance to persist.
    * @throws NullPointerException
    *            If entity is null.
    */
   void persist(T entity) throws NullPointerException;

   /**
    * Merge entity.
    * 
    * @param entity
    *           Entity instance to merge.
    * @return Merged instance.
    * @throws NullPointerException
    *            If entity is null or entity id is null.
    */
   T update(T entity) throws NullPointerException;

   /**
    * Delete entity.
    * 
    * @param entity
    *           Entity instance to delete.
    * @throws NullPointerException
    *            If entity is null or entity id is null.
    */
   void delete(T entity) throws NullPointerException;

   /**
    * Delete entity by id.
    * 
    * @param id
    *           Entity identifier to delete.
    * @param persistenceClass
    *           Entity class.
    * @throws NullPointerException
    *            If id is null or persistence class is null.
    */
   void delete(Id id, Class<T> persistenceClass) throws NullPointerException;

   /**
    * Find entity by identifier.
    * 
    * @param id
    *           Entity identifier for search.
    * @param persistenceClass
    *           Entity class.
    * @return Entity with the given identifier.
    * @throws NullPointerException
    *            If id is null or persistence class is null.
    *
    */
   T findById(Id id, Class<T> persistenceClass) throws NullPointerException;

   Long count(Class<T> persistenceClass);

   List<T> find(int startPosition, int maxResults, String sortField, OrderBy sortDirection,
         Class<T> persistenceClass);

   <V> T findByUniqueField(String field, V fieldValue, Class<T> persistenceClass);

   List<T> findByFilter(Map<String, Object> filters, Class<T> persistenceClass);

   <V> List<T> findBySingleFilter(String filterField, V filterValue, Class<T> persistenceClass);

   List<T> findByQuery(CriteriaQuery<T> query);

   T findSingleByQuery(CriteriaQuery<T> query);
}
