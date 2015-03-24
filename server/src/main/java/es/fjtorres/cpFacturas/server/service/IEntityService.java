package es.fjtorres.cpFacturas.server.service;

import es.fjtorres.cpFacturas.common.exception.ValidationException;

public interface IEntityService<E, D, Id> {

   Class<E> getEntityClass();

   Class<D> getDtoClass();

   void add(D dto) throws ValidationException;

   void update(D dto) throws ValidationException;

   void delete(Id id);
   
   D findById(Id id);
}
