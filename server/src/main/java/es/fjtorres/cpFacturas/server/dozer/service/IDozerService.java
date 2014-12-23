package es.fjtorres.cpFacturas.server.dozer.service;

import java.util.List;

/**
 * 
 * @author fjtorres
 *
 * @param <O>
 *           Origin type.
 * @param <T>
 *           Destination type.
 */
public interface IDozerService {

   <O, T> T convert(O origin, Class<T> destinationClass);

   <O, T> List<T> convert(List<O> list, Class<T> destinationClass);

}
