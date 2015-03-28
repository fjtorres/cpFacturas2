package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

   private boolean existCode(final String pCode) {
      boolean exist = false;

      if (StringUtils.isNotBlank(pCode)) {
         try {
            exist = findEntityByCode(pCode) != null;
         } catch (final NoResultException e) {
            exist = false;
         }
      }

      return exist;
   }

   private Customer findEntityByCode(final String pCode) {
      return getPersistenceService().findByUniqueField(CustomerMetadata.FIELD_CODE, pCode,
            getEntityClass());
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

   @Override
   public List<CustomerDto> findByText(final String pSearchText) {
      final CriteriaBuilder builder = getPersistenceService().getEntityManager()
            .getCriteriaBuilder();
      final CriteriaQuery<Customer> query = builder.createQuery(getEntityClass());
      final Root<Customer> from = query.from(getEntityClass());
      // query.select(builder.construct(getEntityClass(), from.get(CustomerMetadata.FIELD_CODE),
      // from.get(CustomerMetadata.FIELD_FIRST_NAME), from.get(CustomerMetadata.FIELD_LAST_NAME)));
      query.select(from);
      if (StringUtils.isNoneBlank(pSearchText)) {
         final String likeText = "%" + pSearchText + "%";
         final Predicate whereFirstName = builder.like(
               builder.upper(from.get(CustomerMetadata.FIELD_FIRST_NAME)), likeText.toUpperCase());
         final Predicate whereLastName = builder.like(
               builder.upper(from.get(CustomerMetadata.FIELD_LAST_NAME)), likeText.toUpperCase());
         final Predicate whereCode = builder.like(
               builder.upper(from.get(CustomerMetadata.FIELD_CODE)), likeText.toUpperCase());
         query.where(builder.or(whereCode, whereFirstName, whereLastName));
      }
      return getBasicService().convert(getPersistenceService().findByQuery(query), getDtoClass());
   }
}
