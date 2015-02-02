package es.fjtorres.cpFacturas.server.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;

public interface IBasicService {

   IValidationService getValidationService();

   IDozerService getDozerService();

   <T> void processValidationErrors(final Set<ConstraintViolation<T>> errors)
         throws ValidationException;

   <T> boolean validate(final T objectToValidate) throws ValidationException;

   <O, T> List<T> convert(final List<O> origin, final Class<T> destinationClass);

   <O, T> T convert(final O origin, final Class<T> destinationClass);

}
