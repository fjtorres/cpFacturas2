package es.fjtorres.cpFacturas.server.service;

import java.util.List;

import es.fjtorres.cpFacturas.common.dto.VehicleModelDto;
import es.fjtorres.cpFacturas.server.model.VehicleModel;

public interface IVehicleModelService extends IEntityService<VehicleModel, VehicleModelDto, Long> {

   List<VehicleModelDto> findModels(String searchValue, Long brandId);
}
