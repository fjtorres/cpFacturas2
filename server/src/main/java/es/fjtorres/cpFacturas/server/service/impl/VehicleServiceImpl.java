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

import es.fjtorres.cpFacturas.common.dto.VehicleDto;
import es.fjtorres.cpFacturas.common.dto.pagination.VehiclePageDto;
import es.fjtorres.cpFacturas.common.exception.ExceptionUtils;
import es.fjtorres.cpFacturas.common.pagination.OrderBy;
import es.fjtorres.cpFacturas.server.model.Vehicle;
import es.fjtorres.cpFacturas.server.model.metadata.VehicleMetadata;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IVehicleService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;

@Named
@Transactional(readOnly = true)
public class VehicleServiceImpl extends AbstractEntityService<Vehicle, VehicleDto, Long> implements
      IVehicleService {

   @Inject
   public VehicleServiceImpl(final IBasicService pBasicService,
         final IPersistenceService<Long, Vehicle> pPersistenceService) {
      super(pBasicService, pPersistenceService);
   }

   @Override
   public Class<Vehicle> getEntityClass() {
      return Vehicle.class;
   }

   @Override
   public Class<VehicleDto> getDtoClass() {
      return VehicleDto.class;
   }

   @Override
   public VehiclePageDto find(final int page, final int pageSize, final String sortField,
         final String sortDirection) throws IllegalArgumentException {
      if (pageSize == 0) {
         ExceptionUtils.throwIllegalArgument("page size cannon't be zero");
      }

      final OrderBy order = OrderBy.fromString(sortDirection);

      List<VehicleDto> dtos = Collections.emptyList();

      final Long total = getPersistenceService().count(getEntityClass());

      if (total > 0) {

         int maxPages = (int) (total / pageSize);

         if (page > maxPages) {
            ExceptionUtils.throwIllegalArgument("the page cannon't be greater than: {0}", maxPages);
         }

         final int startPosition = page == 0 ? page : (page * pageSize);

         final List<Vehicle> entities = getPersistenceService().find(startPosition, pageSize,
               sortField, order, getEntityClass());

         dtos = getBasicService().convert(entities, getDtoClass());
      }

      final VehiclePageDto pageWrapper = new VehiclePageDto();
      pageWrapper.setList(dtos);
      pageWrapper.setTotal(total);
      return pageWrapper;
   }

   @Override
   public List<VehicleDto> findByText(final String pSearchText) {
      final CriteriaBuilder builder = getPersistenceService().getEntityManager()
            .getCriteriaBuilder();
      final CriteriaQuery<Vehicle> query = builder.createQuery(getEntityClass());
      final Root<Vehicle> from = query.from(getEntityClass());
      query.select(from);
      if (StringUtils.isNoneBlank(pSearchText)) {
         final String likeText = "%" + pSearchText + "%";
         final Predicate whereRegistration = builder.like(
               builder.upper(from.get(VehicleMetadata.FIELD_REGISTRATION)), likeText.toUpperCase());
         query.where(whereRegistration);
      }
      return getBasicService().convert(getPersistenceService().findByQuery(query), getDtoClass());
   }

}
