package es.fjtorres.cpFacturas.rest.api;

import javax.ws.rs.core.Response;

public interface IVehicleBrandResource {

   Response findBrands(String searchValue);
}
