package es.fjtorres.cpFacturas.server.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.VehicleModelDto;
import es.fjtorres.cpFacturas.server.model.VehicleBrand;
import es.fjtorres.cpFacturas.server.model.VehicleModel;
import es.fjtorres.cpFacturas.server.model.metadata.VehicleModelMetadata;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IVehicleModelService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;

@Named
@Transactional(readOnly = true)
public class VehicleModelServiceImpl extends
      AbstractEntityService<VehicleModel, VehicleModelDto, Long> implements IVehicleModelService {

   @Inject
   public VehicleModelServiceImpl(final IBasicService pBasicService,
         final IPersistenceService<Long, VehicleModel> pPersistenceService) {
      super(pBasicService, pPersistenceService);
   }

   @Override
   public Class<VehicleModel> getEntityClass() {
      return VehicleModel.class;
   }

   @Override
   public Class<VehicleModelDto> getDtoClass() {
      return VehicleModelDto.class;
   }

   @Override
   public List<VehicleModelDto> findModels(String pSearchValue, Long pBrandId) {
      final EntityManager entityManager = getPersistenceService().getEntityManager();

      final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      final CriteriaQuery<VehicleModel> query = builder.createQuery(getEntityClass());
      final Root<VehicleModel> from = query.from(getEntityClass());
      query.select(from);

      javax.persistence.criteria.Join<VehicleModel, VehicleBrand> join = from
            .join(VehicleModelMetadata.FIELD_BRAND);
      query.where(builder.like(from.get(VehicleModelMetadata.FIELD_NAME), pSearchValue)).where(
            builder.equal(join.get("id"), pBrandId));

      return getBasicService().convert(getPersistenceService().findByQuery(query), getDtoClass());
   }

}
