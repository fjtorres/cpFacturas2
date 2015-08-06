package es.fjtorres.cpFacturas.rest.api;

import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.common.dto.VehicleDto;

public interface IVehicleResource {
   Response find(int page, int pageSize, String sortField, String sortDirection);

   Response add(VehicleDto dto);

   Response update(VehicleDto dto);

   Response delete(Long id);
   
   Response findById(Long id);
   
   Response findByText(String searchText);

}
