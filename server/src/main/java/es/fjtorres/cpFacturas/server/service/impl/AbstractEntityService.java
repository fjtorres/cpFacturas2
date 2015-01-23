package es.fjtorres.cpFacturas.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;
import es.fjtorres.cpFacturas.server.service.IValidationService;

public abstract class AbstractEntityService {

   private final IDozerService dozerService;

   private final IValidationService validationService;

   public AbstractEntityService(final IDozerService pDozerService,
         final IValidationService pValidationService) {
      this.dozerService = pDozerService;
      this.validationService = pValidationService;
   }

   protected <O, T> T convert(final O origin, final Class<T> destinationClass) {
      return getDozerService().convert(origin, destinationClass);
   }

   protected <O, T> List<T> convert(final List<O> origin,
         final Class<T> destinationClass) {
      return getDozerService().convert(origin, destinationClass);
   }

   /**
    * Validate objectToValidate and throw exception if has errors.
    * 
    * @return Always true, except when an exception occurs.
    * @throws ValidationException
    *            If objectToValidate has errors.
    */
   protected <T> boolean validate(final T objectToValidate)
         throws ValidationException {
      final Set<ConstraintViolation<T>> errors = getValidationService()
            .validate(objectToValidate);
      if (errors != null && !errors.isEmpty()) {
         processValidationErrors(errors);
      }

      return true;
   }

   protected <T> void processValidationErrors(
         final Set<ConstraintViolation<T>> errors) throws ValidationException {
      List<String> errorsMsg = new ArrayList<String>(errors.size());
      for (ConstraintViolation<T> error : errors) {
         errorsMsg.add(error.getMessage());
      }

      throw new ValidationException(errorsMsg);
   }

   public IDozerService getDozerService() {
      return dozerService;
   }

   public IValidationService getValidationService() {
      return validationService;
   }
}
