package es.fjtorres.cpFacturas.server.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.VehicleModelDto;
import es.fjtorres.cpFacturas.server.model.VehicleModel;
import es.fjtorres.cpFacturas.server.model.metadata.VehicleModelMetadata;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IVehicleModelService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;
import es.fjtorres.cpFacturas.server.service.persistence.criteria.Join;
import es.fjtorres.cpFacturas.server.service.persistence.criteria.SearchInfo;
import es.fjtorres.cpFacturas.server.service.persistence.criteria.conditions.Condition;
import es.fjtorres.cpFacturas.server.service.persistence.criteria.conditions.LikeCondition;

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
      final SearchInfo search = new SearchInfo();
      search.addCondition(new LikeCondition(VehicleModelMetadata.FIELD_NAME, pSearchValue));
      search.addCondition(new Condition<Long>("brand.id", pBrandId));
      search.addJoin(new Join(VehicleModelMetadata.FIELD_BRAND));
      
      return getBasicService().convert(
            getPersistenceService().findByFilter(getEntityClass(), search), getDtoClass());
   }

}
