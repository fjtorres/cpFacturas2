package es.fjtorres.cpFacturas.server.service.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import es.fjtorres.cpFacturas.common.CustomerType;
import es.fjtorres.cpFacturas.server.model.ContactData;
import es.fjtorres.cpFacturas.server.model.Customer;

public class ValidationServiceImplTest {

    private static final String ERROR_INVALID_FIELDS = "This object has invalid fields";
    private static final String ERROR_VALID_FIELDS = "This object is valid";
    private static final String STR_TEST = "TEST";
    private static final String STR_VALID_EMAIL = "TEST@TEST.com";

    private ValidationServiceImpl testInstance;

    private Customer TEST_OBJECT;

    @BeforeMethod
    public void beforeMethod() {
        this.testInstance = new ValidationServiceImpl();

        final ContactData cd = new ContactData();
        cd.setFirstPhoneNumber(STR_TEST);
        cd.setEmail(STR_VALID_EMAIL);

        TEST_OBJECT = new Customer();
        TEST_OBJECT.setCode(STR_TEST);
        TEST_OBJECT.setContactData(cd);
        TEST_OBJECT.setFirstName(STR_TEST);
        TEST_OBJECT.setLastName(STR_TEST);
        TEST_OBJECT.setType(CustomerType.PERSON);
    }

    @Test
    public void isValidTestWithAllInvalidFields() {
        assertEquals(testInstance.isValid(new Customer()), false, ERROR_INVALID_FIELDS);
    }

    @Test
    public void isValidTestWithInvalidMail() {
        TEST_OBJECT.getContactData().setEmail(STR_TEST);
        assertEquals(testInstance.isValid(TEST_OBJECT), false, ERROR_INVALID_FIELDS);
    }

    @Test
    public void isValidWithValidFields() {
        assertEquals(testInstance.isValid(TEST_OBJECT), true, ERROR_VALID_FIELDS);
    }

    @Test
    public void validateWithAllInvalidFields() {
        checkConstraintResult(testInstance.validate(new Customer()), 4);
    }

    @Test
    public void validateWithInvalidMail() {
        TEST_OBJECT.getContactData().setEmail(STR_TEST);
        checkConstraintResult(testInstance.validate(TEST_OBJECT), 1);
    }

    @Test
    public void validateWithValidFields() {
        checkConstraintResult(testInstance.validate(TEST_OBJECT), 0);
    }

    private void checkConstraintResult(final Set<ConstraintViolation<Customer>> result,
            final int expectedErrors) {
        assertNotNull(result, expectedErrors == 0 ? "This object doesn't have errors."
                : "This object has invalid fields.");
        assertEquals(result.size(), expectedErrors, "Expected " + expectedErrors + " errors.");
    }
}
