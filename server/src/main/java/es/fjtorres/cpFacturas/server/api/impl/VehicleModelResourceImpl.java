package es.fjtorres.cpFacturas.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.VEHICLES_MODEL_PATH;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.rest.api.IVehicleModelResource;
import es.fjtorres.cpFacturas.server.service.IVehicleModelService;

@Named
@Path(VEHICLES_MODEL_PATH)
public class VehicleModelResourceImpl extends AbstractResource implements IVehicleModelResource {

   /**
    * 
    */
   private static final long serialVersionUID = -4656153043114773236L;

   private final IVehicleModelService service;

   @Inject
   public VehicleModelResourceImpl(final IVehicleModelService pService) {
      this.service = pService;
   }

   public IVehicleModelService getService() {
      return service;
   }

   @Override
   @GET
   public Response findModels(@QueryParam("searchValue") final String pSearchValue,
         @QueryParam("brandId") final Long pBrandId) {
      return Response.ok(getService().findModels(pSearchValue, pBrandId)).build();
   }

}
