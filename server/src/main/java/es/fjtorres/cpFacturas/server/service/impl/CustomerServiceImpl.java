package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.pagination.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.ExceptionUtils;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.model.Customer;
import es.fjtorres.cpFacturas.server.model.metadata.CustomerMetadata;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.ICustomerService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;

@Named
@Transactional(readOnly = true)
public class CustomerServiceImpl extends AbstractEntityService<Customer, CustomerDto, Long>
      implements ICustomerService {

   @Inject
   public CustomerServiceImpl(final IBasicService pBasicService,
         final IPersistenceService<Long, Customer> pPersistenceService) {
      super(pBasicService, pPersistenceService);
   }

   @Override
   public Class<Customer> getEntityClass() {
      return Customer.class;
   }

   @Override
   public Class<CustomerDto> getDtoClass() {
      return CustomerDto.class;
   }

   @Override
   public CustomerPageDto find(final int page, final int pageSize, final String sortField,
         final String sortDirection) {

      if (pageSize == 0) {
         ExceptionUtils.throwIllegalArgument("page size cannon't be zero");
      }

      final OrderBy order = OrderBy.fromString(sortDirection);

      List<CustomerDto> dtos = Collections.emptyList();

      final Long total = getPersistenceService().count(getEntityClass());

      if (total > 0) {

         int maxPages = (int) (total / pageSize);

         if (page > maxPages) {
            ExceptionUtils.throwIllegalArgument("the page cannon't be greater than: {0}", maxPages);
         }

         final int startPosition = page == 0 ? page : (page * pageSize);

         final List<Customer> entities = getPersistenceService().find(startPosition, pageSize,
               sortField, order, getEntityClass());

         dtos = getBasicService().convert(entities, getDtoClass());
      }

      final CustomerPageDto pageWrapper = new CustomerPageDto();
      pageWrapper.setList(dtos);
      pageWrapper.setTotal(total);
      return pageWrapper;
   }

   @Override
   @Transactional
   public void add(final CustomerDto pDto) throws ValidationException {
      Objects.requireNonNull(pDto, ERROR_DTO_NULL);
      if (existCode(pDto.getCode())) {
         throw new ValidationException("The customer code exist");
      } else {
         super.add(pDto);
      }
   }

   private boolean existCode(final String pCode) {
      boolean exist = false;

      if (StringUtils.isNotBlank(pCode)) {
         exist = findEntityByCode(pCode) == null;
      }

      return exist;
   }

   private Customer findEntityByCode(final String pCode) {
      return getPersistenceService().findByUniqueField(CustomerMetadata.FIELD_CODE, pCode,
            getEntityClass());
   }

}
