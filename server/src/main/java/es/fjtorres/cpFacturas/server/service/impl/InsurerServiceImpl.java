package es.fjtorres.cpFacturas.server.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.InsurerDto;
import es.fjtorres.cpFacturas.common.dto.pagination.InsurerPageDto;
import es.fjtorres.cpFacturas.common.exception.ExceptionUtils;
import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.model.Insurer;
import es.fjtorres.cpFacturas.server.model.metadata.CustomerMetadata;
import es.fjtorres.cpFacturas.server.model.metadata.InsurerMetadata;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IInsurerService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;

@Named
@Transactional(readOnly = true)
public class InsurerServiceImpl extends
      AbstractEntityService<Insurer, InsurerDto, Long> implements
      IInsurerService {

   @Inject
   public InsurerServiceImpl(final IBasicService pBasicService,
         final IPersistenceService<Long, Insurer> pPersistenceService) {
      super(pBasicService, pPersistenceService);
   }

   @Override
   public Class<Insurer> getEntityClass() {
      return Insurer.class;
   }

   @Override
   public Class<InsurerDto> getDtoClass() {
      return InsurerDto.class;
   }

   @Override
   public InsurerPageDto find(final int page, final int pageSize,
         final String sortField, final String sortDirection)
         throws IllegalArgumentException {
      if (pageSize == 0) {
         ExceptionUtils.throwIllegalArgument("page size cannon't be zero");
      }

      final OrderBy order = OrderBy.fromString(sortDirection);

      List<InsurerDto> dtos = Collections.emptyList();

      final Long total = getPersistenceService().count(getEntityClass());

      if (total > 0) {

         int maxPages = (int) (total / pageSize);

         if (page > maxPages) {
            ExceptionUtils.throwIllegalArgument(
                  "the page cannon't be greater than: {0}", maxPages);
         }

         final int startPosition = page == 0 ? page : (page * pageSize);

         final List<Insurer> entities = getPersistenceService().find(
               startPosition, pageSize, sortField, order, getEntityClass());

         dtos = getBasicService().convert(entities, getDtoClass());
      }

      final InsurerPageDto pageWrapper = new InsurerPageDto();
      pageWrapper.setList(dtos);
      pageWrapper.setTotal(total);
      return pageWrapper;
   }
   
   @Override
   public List<InsurerDto> findByText(final String pSearchText) {
      final CriteriaBuilder builder = getPersistenceService().getEntityManager()
            .getCriteriaBuilder();
      final CriteriaQuery<Insurer> query = builder.createQuery(getEntityClass());
      final Root<Insurer> from = query.from(getEntityClass());
      query.select(from);
      if (StringUtils.isNoneBlank(pSearchText)) {
         final String likeText = "%" + pSearchText + "%";
         final Predicate whereFirstName = builder.like(
               builder.upper(from.get(InsurerMetadata.FIELD_NAME)), likeText.toUpperCase());
         final Predicate whereCode = builder.like(
               builder.upper(from.get(CustomerMetadata.FIELD_CODE)), likeText.toUpperCase());
         query.where(builder.or(whereCode, whereFirstName));
      }
      return getBasicService().convert(getPersistenceService().findByQuery(query), getDtoClass());
   }

}
