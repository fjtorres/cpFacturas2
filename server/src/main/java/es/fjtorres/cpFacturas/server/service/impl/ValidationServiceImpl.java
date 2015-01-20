package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Set;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import es.fjtorres.cpFacturas.server.service.IValidationService;

@Named
@Singleton
public class ValidationServiceImpl implements IValidationService {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private Validator getValidator() {
        return validatorFactory.getValidator();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(final T pObjectToValidate,
            final Class<?>... pGroups) {
        return getValidator().validate(pObjectToValidate, pGroups);
    }

    @Override
    public <T> boolean isValid(final T pObjectToValidate, final Class<?>... pGroups) {
        return validate(pObjectToValidate, pGroups).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(final T pObjectToValidate,
            final String pPropertyName, final Class<?>... pGroups) {
        return getValidator().validateProperty(pObjectToValidate, pPropertyName, pGroups);
    }

    @Override
    public <T> boolean isValid(final T pObjectToValidate, final String pPropertyName,
            final Class<?>... pGroups) {
        return validateProperty(pObjectToValidate, pPropertyName, pGroups).isEmpty();
    }

    @Override
    public <T> boolean isValidValue(final Class<T> pType, final String pPropertyName,
            final Object pValue, final Class<?>... pGroups) {
        return validateValue(pType, pPropertyName, pValue, pGroups).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(final Class<T> pType,
            final String pPropertyName, final Object pValue, final Class<?>... pGroups) {
        return getValidator().validateValue(pType, pPropertyName, pValue, pGroups);
    }
}
