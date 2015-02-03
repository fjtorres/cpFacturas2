package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.server.MockitoUtils;
import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;
import es.fjtorres.cpFacturas.server.model.Customer;
import es.fjtorres.cpFacturas.server.service.IPersistenceService;
import es.fjtorres.cpFacturas.server.service.IValidationService;

public class CustomerServiceImplTest {

   private CustomerServiceImpl testInstance;

   @Mock
   private IDozerService mockDozerService;

   @Mock
   private IValidationService mockValidationService;

   @Mock
   private IPersistenceService<Long, Customer> mockPersistenceService;

   @BeforeMethod
   public void beforeMethod() {
      MockitoAnnotations.initMocks(this);
      testInstance = new CustomerServiceImpl(new BasicService(mockDozerService,
            mockValidationService), mockPersistenceService);
   }

   @AfterMethod
   public void afterMethod() {
      Mockito.validateMockitoUsage();
   }

   @Test(expectedExceptions = {
      NullPointerException.class
   })
   public void addNullDto() throws ValidationException {
      testInstance.add(null);
   }

   @Test(expectedExceptions = {
      ValidationException.class
   })
   public void addInvalidDto() throws ValidationException {
      final Set<ConstraintViolation<Customer>> errors = MockitoUtils
            .oneMinimalisticMockConstraintViolation();
      final CustomerDto dto = new CustomerDto();
      final Customer entity = new Customer();

      Mockito.when(mockDozerService.convert(dto, Customer.class)).thenReturn(
            entity);
      Mockito.when(mockValidationService.validate(entity)).thenReturn(errors);
      testInstance.add(dto);
   }

   @Test
   public void delete() {
      throw new RuntimeException("Test not implemented");
   }

   @Test(expectedExceptions = {
      NullPointerException.class
   })
   public void deleteWithNullId() {
      testInstance.delete(null);
   }

   @Test
   public void find() {
      throw new RuntimeException("Test not implemented");
   }

   @Test
   public void findById() {
      throw new RuntimeException("Test not implemented");
   }

   @Test(expectedExceptions = {
      NullPointerException.class
   })
   public void findByIdWithNullId() {
      testInstance.findById(null);
   }

   @Test
   public void update() {
      throw new RuntimeException("Test not implemented");
   }

   @Test(expectedExceptions = {
      NullPointerException.class
   })
   public void updateNullDto() throws ValidationException {
      testInstance.update(null);
   }
}
