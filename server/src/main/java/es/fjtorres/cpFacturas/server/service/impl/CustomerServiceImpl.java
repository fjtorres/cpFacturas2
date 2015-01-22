package es.fjtorres.cpFacturas.server.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.ExceptionUtils;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;
import es.fjtorres.cpFacturas.server.model.Customer;
import es.fjtorres.cpFacturas.server.service.ICustomerService;
import es.fjtorres.cpFacturas.server.service.IPersistenceService;
import es.fjtorres.cpFacturas.server.service.IValidationService;

@Named
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService {

   private final IDozerService dozerService;

   private final IPersistenceService<Long, Customer> persistenceService;

   private final IValidationService validationService;

   @Inject
   public CustomerServiceImpl(final IDozerService pDozerService,
         final IPersistenceService<Long, Customer> pPersistenceService,
         final IValidationService pValidationService) {
      this.dozerService = pDozerService;
      this.persistenceService = pPersistenceService;
      this.validationService = pValidationService;
   }

   public IDozerService getDozerService() {
      return dozerService;
   }

   public IPersistenceService<Long, Customer> getPersistenceService() {
      return persistenceService;
   }

   @Override
   public CustomerPageDto find(final int page, final int pageSize,
         final String sortField, final String sortDirection) {

      if (pageSize == 0) {
         ExceptionUtils.throwIllegalArgument("page size cannon't be zero");
      }

      OrderBy order = null;
      try {
         order = OrderBy.valueOf(sortDirection);
      } catch (final IllegalArgumentException iae) {
         ExceptionUtils
               .throwIllegalArgument("sort direction isn't valid. Only ASC or DESC.");
      }

      List<CustomerDto> dtos = Collections.emptyList();

      final Long total = getPersistenceService().count(Customer.class);

      if (total > 0) {

         int maxPages = (int) (total / pageSize);

         if (page > maxPages) {
            ExceptionUtils.throwIllegalArgument(
                  "the page cannon't be greater than: {0}", maxPages);
         }

         final int startPosition = page == 0 ? page : (page * pageSize);

         final List<Customer> entities = getPersistenceService().find(
               startPosition, pageSize, sortField, order, Customer.class);

         dtos = getDozerService().convert(entities, CustomerDto.class);
      }

      final CustomerPageDto pageWrapper = new CustomerPageDto();
      pageWrapper.setList(dtos);
      pageWrapper.setTotal(getPersistenceService().count(Customer.class));
      return pageWrapper;
   }

   @Override
   public CustomerDto findById(final Long pId) {
      Objects.requireNonNull(pId, "ID cannon't be null");

      final Customer entity = getPersistenceService().findById(pId,
            Customer.class);
      return getDozerService().convert(entity, CustomerDto.class);
   }

   @Override
   @Transactional
   public void add(final CustomerDto pDto) throws ValidationException {
      Objects.requireNonNull(pDto, "DTO cannon't be null");

      final Customer entity = getDozerService().convert(pDto, Customer.class);
      final Set<ConstraintViolation<Customer>> errors = getValidationService()
            .validate(entity);
      if (errors == null || errors.isEmpty()) {
         getPersistenceService().persist(entity);
      } else {
         processValidationErrors(errors);
      }
   }

   @Override
   @Transactional
   public void update(final CustomerDto pDto) throws ValidationException {
      Objects.requireNonNull(pDto, "DTO cannon't be null");

      final Customer entity = getDozerService().convert(pDto, Customer.class);
      final Set<ConstraintViolation<Customer>> errors = getValidationService()
            .validate(entity);
      if (errors == null || errors.isEmpty()) {
         getPersistenceService().update(entity);
      } else {
         processValidationErrors(errors);
      }
   }

   @Override
   @Transactional
   public void delete(final Long pId) {
      Objects.requireNonNull(pId, "ID cannon't be null");

      getPersistenceService().delete(pId, Customer.class);
   }

   public IValidationService getValidationService() {
      return validationService;
   }

   private <T> void processValidationErrors(
         final Set<ConstraintViolation<T>> errors) throws ValidationException {
      List<String> errorsMsg = new ArrayList<String>(errors.size());
      for (ConstraintViolation<T> error : errors) {
         errorsMsg.add(error.getMessage());
      }

      throw new ValidationException(errorsMsg);
   }

   @Override
   public CustomerDto findByCode(final String pCode) {
      Objects.requireNonNull(pCode, "Code cannon't be null");
      if (StringUtils.isBlank(pCode)) {
         ExceptionUtils.throwIllegalArgument("code cannon't be empty");
      }

      // FIXME Create query search

      return null;
   }
}
