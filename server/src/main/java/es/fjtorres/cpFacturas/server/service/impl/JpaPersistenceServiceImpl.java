package es.fjtorres.cpFacturas.server.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.model.AbstractEntity;
import es.fjtorres.cpFacturas.server.service.IPersistenceService;

@Named("jpaPersistenceService")
public class JpaPersistenceServiceImpl<Id extends Serializable, T extends AbstractEntity<Id>>
        implements IPersistenceService<Id, T> {

    private static final String ERROR_PERSISTENT_CONTEXT_NULL = "persistent context cannon't be null";
    private static final String ERROR_PERSISTENT_CLASS_NULL = "Persistence class cannon't be null";
    private static final String ERROR_ENTITY_ID_NULL = "Entity identifier cannon't be null";
    private static final String ERROR_ENTITY_NULL = "Entity cannon't be null";

    private final PersistenceContextWrapper contextWrapper;

    @Inject
    public JpaPersistenceServiceImpl(final PersistenceContextWrapper pContextWrapper) {
        this.contextWrapper = pContextWrapper;

        if (contextWrapper == null || contextWrapper.getEntityManager() == null) {
            throw new IllegalArgumentException(ERROR_PERSISTENT_CONTEXT_NULL);
        }
    }

    private EntityManager getEntityManager() {
        return contextWrapper.getEntityManager();
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

        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> query = cb.createQuery(pPersistenceClass);
        final Root<T> select = query.from(pPersistenceClass);
        query.select(select);

        if (sortDirection != null && StringUtils.isNotBlank(sortField)
                && isField(select, sortField)) {

            switch (sortDirection) {
            case ASC:
                query.orderBy(cb.asc(select.get(sortField)));
                break;
            case DESC:
                query.orderBy(cb.desc(select.get(sortField)));
                break;
            }
        }

        return getEntityManager().createQuery(query).setFirstResult(startPosition)
                .setMaxResults(maxResults).getResultList();
    }

    @Override
    public Long count(final Class<T> pPersistenceClass) {
        Objects.requireNonNull(pPersistenceClass, ERROR_PERSISTENT_CLASS_NULL);

        final CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Long> query = qb.createQuery(Long.class);
        query.select(qb.count(query.from(pPersistenceClass)));
        return getEntityManager().createQuery(query).getSingleResult();
    }

    private void checkEntity(final T pEntity) throws NullPointerException {
        Objects.requireNonNull(pEntity, ERROR_ENTITY_NULL);
    }

    private void checkEntityAndIdentifier(final T pEntity) throws NullPointerException {
        checkEntity(pEntity);
        Objects.requireNonNull(pEntity.getId(), ERROR_ENTITY_ID_NULL);
    }

    private boolean isField(final Root<T> root, final String field) {
        boolean exist = true;
        try {
            root.get(field);
        } catch (final IllegalArgumentException iae) {
            exist = false;
        }
        return exist;
    }
}