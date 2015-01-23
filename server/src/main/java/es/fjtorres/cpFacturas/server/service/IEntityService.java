package es.fjtorres.cpFacturas.server.service;

public interface IEntityService<E, D> {

   Class<E> getEntityClass();

   Class<D> getDtoClass();
}
