package es.fjtorres.cpFacturas.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.VEHICLES_BRAND_PATH;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.rest.api.IVehicleBrandResource;
import es.fjtorres.cpFacturas.server.service.IVehicleBrandService;

@Named
@Path(VEHICLES_BRAND_PATH)
public class VehicleBrandResourceImpl extends AbstractResource implements IVehicleBrandResource {

   /**
    * 
    */
   private static final long serialVersionUID = 396271337343210177L;

   private final IVehicleBrandService service;

   @Inject
   public VehicleBrandResourceImpl(final IVehicleBrandService pService) {
      this.service = pService;
   }

   public IVehicleBrandService getService() {
      return service;
   }

   @Override
   @GET
   public Response findBrands(@QueryParam("searchValue") final String pSearchValue) {
      return Response.ok(getService().findBrands(pSearchValue)).build();
   }

}
