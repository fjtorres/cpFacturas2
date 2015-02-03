package es.fjtorres.cpFacturas.server.service;

import es.fjtorres.cpFacturas.common.dto.VehicleDto;
import es.fjtorres.cpFacturas.common.dto.pagination.VehiclePageDto;
import es.fjtorres.cpFacturas.server.model.Vehicle;

public interface IVehicleService extends
      IEntityService<Vehicle, VehicleDto, Long> {

   /**
    * 
    * @param page
    * @param pageSize
    * @param sortField
    * @param sortDirection
    * @return
    * @throws IllegalArgumentException
    *            If any parameter are invalid.
    */
   VehiclePageDto find(int page, int pageSize, String sortField,
         String sortDirection) throws IllegalArgumentException;

}
