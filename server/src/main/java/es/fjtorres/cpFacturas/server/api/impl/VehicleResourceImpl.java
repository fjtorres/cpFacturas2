package es.fjtorres.cpFacturas.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_NUMBER;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_SIZE;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_SORT_DIRECTION;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_SORT_FIELD;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.VEHICLES_PATH;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.common.dto.VehicleDto;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.common.pagination.Page;
import es.fjtorres.cpFacturas.rest.api.IVehicleResource;
import es.fjtorres.cpFacturas.server.service.IVehicleService;

@Named
@Path(VEHICLES_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleResourceImpl extends AbstractResource implements
      IVehicleResource {

   /**
    * 
    */
   private static final long serialVersionUID = -155895991984122841L;

   private final IVehicleService service;

   @Inject
   public VehicleResourceImpl(final IVehicleService pService) {
      this.service = pService;
   }

   public IVehicleService getService() {
      return service;
   }

   @Override
   @GET
   public Response find(
         @DefaultValue("0") @QueryParam(PAGE_NUMBER) final int page,
         @DefaultValue("10") @QueryParam(PAGE_SIZE) final int pageSize,
         @QueryParam(PAGE_SORT_FIELD) final String sortField,
         @DefaultValue("ASC") @QueryParam(PAGE_SORT_DIRECTION) final String sortDirection) {
      Page<VehicleDto> wrapper = null;
      try {
         wrapper = getService().find(page, pageSize, sortField, sortDirection);
      } catch (final IllegalArgumentException iae) {
         badRequest(iae.getMessage());
      }
      return Response.ok(wrapper).build();

   }

   @Override
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response add(final VehicleDto pDto) {
      try {
         getService().add(pDto);
      } catch (final ValidationException ve) {
         badRequest(ve.getErrors());
      }

      return Response.ok().build();
   }

   @Override
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   public Response update(final VehicleDto pDto) {
      try {
         getService().update(pDto);
      } catch (final ValidationException ve) {
         badRequest(ve.getErrors());
      }
      return Response.ok().build();
   }

   @Override
   @DELETE
   @Path("{id}")
   public Response delete(@PathParam("id") final Long pId) {
      getService().delete(pId);
      return Response.ok().build();
   }
}
