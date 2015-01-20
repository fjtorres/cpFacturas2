package es.fjtorres.cpFacturas.server.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface IValidationService {

    <T> Set<ConstraintViolation<T>> validate(T objectToValidate, Class<?>... groups);

    <T> boolean isValid(T objectToValidate, Class<?>... groups);

    <T> Set<ConstraintViolation<T>> validateProperty(T objectToValidate, String propertyName,
            Class<?>... groups);

    <T> boolean isValid(T objectToValidate, String propertyName, Class<?>... groups);

    <T> Set<ConstraintViolation<T>> validateValue(Class<T> type, String propertyName, Object value,
            Class<?>... groups);

    <T> boolean isValidValue(Class<T> type, String propertyName, Object value, Class<?>... groups);
}
