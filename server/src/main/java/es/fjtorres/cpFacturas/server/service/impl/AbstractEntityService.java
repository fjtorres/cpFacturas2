package es.fjtorres.cpFacturas.server.service.impl;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.AbstractDto;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.server.model.AbstractEntity;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IEntityService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;

public abstract class AbstractEntityService<E extends AbstractEntity<Id>, D extends AbstractDto<Id>, Id extends Serializable>
      implements IEntityService<E, D, Id> {

   protected static final String ERROR_ID_NULL = "ID cannon't be null";

   protected static final String ERROR_DTO_NULL = "DTO cannon't be null";

   private static final Long DEFAULT_ID = -1L;

   private final IPersistenceService<Id, E> persistenceService;

   private final IBasicService basicService;

   public AbstractEntityService(final IBasicService pBasicService,
         final IPersistenceService<Id, E> pPersistenceService) {
      this.basicService = pBasicService;
      this.persistenceService = pPersistenceService;
   }

   public IBasicService getBasicService() {
      return basicService;
   }

   public IPersistenceService<Id, E> getPersistenceService() {
      return persistenceService;
   }

   @Override
   @Transactional
   public void add(final D pDto) throws ValidationException {
      Objects.requireNonNull(pDto, ERROR_DTO_NULL);

      final E entity = getBasicService().convert(pDto, getEntityClass());
      if (getBasicService().validate(entity)) {
         if (DEFAULT_ID.equals(entity.getId())) {
            entity.setId(null);
         }
         getPersistenceService().persist(entity);
      }
   }

   @Override
   @Transactional
   public void update(final D pDto) throws ValidationException {
      Objects.requireNonNull(pDto, ERROR_DTO_NULL);

      final E entity = getBasicService().convert(pDto, getEntityClass());
      if (getBasicService().validate(entity)) {
         getPersistenceService().update(entity);
      }
   }

   @Override
   @Transactional
   public void delete(final Id pId) {
      Objects.requireNonNull(pId, ERROR_ID_NULL);
      getPersistenceService().delete(pId, getEntityClass());
   }

   @Override
   @Transactional(readOnly = true)
   public D findById(final Id pId) {
      Objects.requireNonNull(pId, ERROR_ID_NULL);

      final E entity = getPersistenceService().findById(pId, getEntityClass());
      return getBasicService().convert(entity, getDtoClass());
   }

}
