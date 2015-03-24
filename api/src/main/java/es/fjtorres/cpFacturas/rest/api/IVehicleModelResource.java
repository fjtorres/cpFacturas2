package es.fjtorres.cpFacturas.rest.api;

import javax.ws.rs.core.Response;

public interface IVehicleModelResource {

   Response findModels(String searchValue, Long brandId);
}
