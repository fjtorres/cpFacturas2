package es.fjtorres.cpFacturas.server.dozer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;

import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;

@Named
public class DozerServiceImpl implements IDozerService {

   private final Mapper dozerMapper;

   @Inject
   public DozerServiceImpl(final Mapper pDozerMapper) {
      this.dozerMapper = pDozerMapper;
   }

   public Mapper getDozerMapper() {
      return dozerMapper;
   }

   @Override
   public <O, T> T convert(final O origin, final Class<T> destinationClass) {
      return getDozerMapper().map(origin, destinationClass);
   }

   @Override
   public <O, T> List<T> convert(final List<O> list,
         final Class<T> destinationClass) {
      List<T> result = Collections.emptyList();
      if (list != null && !list.isEmpty()) {
         result = new ArrayList<>();
         for (final O origin : list) {
            result.add(convert(origin, destinationClass));
         }
      }
      return result;
   }

}
