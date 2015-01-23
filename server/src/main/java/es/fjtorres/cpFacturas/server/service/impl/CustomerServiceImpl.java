package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

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
public class CustomerServiceImpl extends AbstractEntityService implements
      ICustomerService {

   private final IPersistenceService<Long, Customer> persistenceService;

   @Inject
   public CustomerServiceImpl(final IDozerService pDozerService,
         final IPersistenceService<Long, Customer> pPersistenceService,
         final IValidationService pValidationService) {
      super(pDozerService, pValidationService);
      this.persistenceService = pPersistenceService;
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

      final OrderBy order = OrderBy.fromString(sortDirection);

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
               startPosition, pageSize, sortField, order, getEntityClass());

         dtos = convert(entities, getDtoClass());
      }

      final CustomerPageDto pageWrapper = new CustomerPageDto();
      pageWrapper.setList(dtos);
      pageWrapper.setTotal(getPersistenceService().count(getEntityClass()));
      return pageWrapper;
   }

   @Override
   public CustomerDto findById(final Long pId) {
      Objects.requireNonNull(pId, "ID cannon't be null");

      final Customer entity = getPersistenceService().findById(pId,
            Customer.class);
      return convert(entity, getDtoClass());
   }

   @Override
   @Transactional
   public void add(final CustomerDto pDto) throws ValidationException {
      Objects.requireNonNull(pDto, "DTO cannon't be null");

      final Customer entity = convert(pDto, getEntityClass());
      if (validate(entity)) {
         getPersistenceService().persist(entity);
      }
   }

   @Override
   @Transactional
   public void update(final CustomerDto pDto) throws ValidationException {
      Objects.requireNonNull(pDto, "DTO cannon't be null");

      final Customer entity = convert(pDto, getEntityClass());
      if (validate(entity)) {
         getPersistenceService().update(entity);
      }
   }

   @Override
   @Transactional
   public void delete(final Long pId) {
      Objects.requireNonNull(pId, "ID cannon't be null");

      getPersistenceService().delete(pId, getEntityClass());
   }

   @Override
   public CustomerDto findByCode(final String pCode) {
      Objects.requireNonNull(pCode, "Code cannon't be null");
      if (StringUtils.isBlank(pCode)) {
         ExceptionUtils.throwIllegalArgument("Code cannon't be empty");
      }

      final Customer entity = getPersistenceService().findByUniqueField("code",
            pCode, getEntityClass());

      return convert(entity, getDtoClass());
   }

   @Override
   public Class<Customer> getEntityClass() {
      return Customer.class;
   }

   @Override
   public Class<CustomerDto> getDtoClass() {
      return CustomerDto.class;
   }
}
