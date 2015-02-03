package es.fjtorres.cpFacturas.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;

import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IValidationService;

@Named
public class BasicService implements IBasicService {

   private final IDozerService dozerService;

   private final IValidationService validationService;

   @Inject
   public BasicService(final IDozerService pDozerService,
         final IValidationService pValidationService) {
      this.dozerService = pDozerService;
      this.validationService = pValidationService;
   }

   @Override
   public <O, T> T convert(final O origin, final Class<T> destinationClass) {
      return getDozerService().convert(origin, destinationClass);
   }

   @Override
   public <O, T> List<T> convert(final List<O> origin,
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
   @Override
   public <T> boolean validate(final T objectToValidate)
         throws ValidationException {
      final Set<ConstraintViolation<T>> errors = getValidationService()
            .validate(objectToValidate);
      if (errors != null && !errors.isEmpty()) {
         processValidationErrors(errors);
      }

      return true;
   }

   @Override
   public <T> void processValidationErrors(
         final Set<ConstraintViolation<T>> errors) throws ValidationException {
      List<String> errorsMsg = new ArrayList<String>(errors.size());
      for (ConstraintViolation<T> error : errors) {
         errorsMsg.add(error.getMessage());
      }

      throw new ValidationException(errorsMsg);
   }

   @Override
   public IDozerService getDozerService() {
      return dozerService;
   }

   @Override
   public IValidationService getValidationService() {
      return validationService;
   }
}
