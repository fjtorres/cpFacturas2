package es.fjtorres.cpFacturas.server.service.impl;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.server.model.AbstractEntity;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IEntityService;
import es.fjtorres.cpFacturas.server.service.IPersistenceService;

public abstract class AbstractEntityService<E extends AbstractEntity<Id>, D, Id extends Serializable>
      implements IEntityService<E, D, Id> {

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
      Objects.requireNonNull(pDto, "DTO cannon't be null");

      final E entity = getBasicService().convert(pDto, getEntityClass());
      if (getBasicService().validate(entity)) {
         getPersistenceService().persist(entity);
      }
   }

   @Override
   @Transactional
   public void update(final D pDto) throws ValidationException {
      Objects.requireNonNull(pDto, "DTO cannon't be null");

      final E entity = getBasicService().convert(pDto, getEntityClass());
      if (getBasicService().validate(entity)) {
         getPersistenceService().update(entity);
      }
   }

   @Override
   @Transactional
   public void delete(final Id pId) {
      Objects.requireNonNull(pId, "ID cannon't be null");
      getPersistenceService().delete(pId, getEntityClass());
   }

}
