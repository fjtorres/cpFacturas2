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
      return getBasicService().convert(
            getPersistenceService().findBySingleFilter(VehicleBrandMetadata.FIELD_NAME,
                  pSearchValue, getEntityClass()), getDtoClass());
   }

}
