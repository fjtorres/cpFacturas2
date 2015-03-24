package es.fjtorres.cpFacturas.server.service;

import java.util.List;

import es.fjtorres.cpFacturas.common.dto.VehicleBrandDto;
import es.fjtorres.cpFacturas.server.model.VehicleBrand;

public interface IVehicleBrandService extends IEntityService<VehicleBrand, VehicleBrandDto, Long> {

   List<VehicleBrandDto> findBrands(String searchValue);
}
