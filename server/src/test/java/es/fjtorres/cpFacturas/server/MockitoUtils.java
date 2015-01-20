package es.fjtorres.cpFacturas.server;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

import org.mockito.Mockito;

/**
 * @author Dmitriy Kopylenko
 * @since 1.0
 */
public final class MockitoUtils {


    private MockitoUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<ConstraintViolation<T>> oneMinimalisticMockConstraintViolation() {

        final ConstraintViolation<T> mockConstraintViolation = Mockito.mock(
                ConstraintViolation.class, Mockito.RETURNS_SMART_NULLS);
        final Path mockPath = Mockito.mock(Path.class);
        Mockito.when(mockPath.toString()).thenReturn("test.property.path");
        Mockito.when(mockConstraintViolation.getPropertyPath()).thenReturn(mockPath);
        Mockito.when(mockConstraintViolation.getMessage()).thenReturn("mockConstraintViolation: error message");
        Mockito.when(mockConstraintViolation.getInvalidValue()).thenReturn("mockConstraintViolation: invalid value");
        Set<ConstraintViolation<T>> setWithErrors = new HashSet<ConstraintViolation<T>>();
        setWithErrors.add(mockConstraintViolation);
        return setWithErrors;
    }
}
