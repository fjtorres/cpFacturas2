package es.fjtorres.cpFacturas.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.ENUM_PATH;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.fjtorres.cpFacturas.common.FuelType;
import es.fjtorres.cpFacturas.rest.api.IEnumResource;

@Named
@Path(ENUM_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class EnumResourceImpl implements IEnumResource {

   @Override
   @GET
   @Path("/fuelTypes")
   public FuelType[] getFuelTypes() {
      return FuelType.values();
   }

}
