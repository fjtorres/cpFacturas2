package es.fjtorres.cpFacturas.server.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import es.fjtorres.cpFacturas.common.dto.VehicleBrandDto;
import es.fjtorres.cpFacturas.server.model.VehicleBrand;
import es.fjtorres.cpFacturas.server.model.metadata.VehicleBrandMetadata;
import es.fjtorres.cpFacturas.server.service.IBasicService;
import es.fjtorres.cpFacturas.server.service.IVehicleBrandService;
import es.fjtorres.cpFacturas.server.service.persistence.IPersistenceService;
import es.fjtorres.cpFacturas.server.service.persistence.criteria.SearchInfo;
import es.fjtorres.cpFacturas.server.service.persistence.criteria.conditions.LikeCondition;

@Named
@Transactional(readOnly = true)
public class VehicleBrandServiceImpl extends
      AbstractEntityService<VehicleBrand, VehicleBrandDto, Long> implements IVehicleBrandService {

   @Inject
   public VehicleBrandServiceImpl(final IBasicService pBasicService,
         final IPersistenceService<Long, VehicleBrand> pPersistenceService) {
      super(pBasicService, pPersistenceService);
   }

   @Override
   public Class<VehicleBrand> getEntityClass() {
      return VehicleBrand.class;
   }

   @Override
   public Class<VehicleBrandDto> getDtoClass() {
      return VehicleBrandDto.class;
   }

   @Override
   public List<VehicleBrandDto> findBrands(final String pSearchValue) {
      final SearchInfo search = new SearchInfo();
      search.addCondition(new LikeCondition(VehicleBrandMetadata.FIELD_NAME, pSearchValue));
      return getBasicService().convert(
            getPersistenceService().findByFilter(getEntityClass(), search), getDtoClass());
   }

}
