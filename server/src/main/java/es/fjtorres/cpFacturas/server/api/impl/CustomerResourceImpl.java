package es.fjtorres.cpFacturas.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.CUSTOMERS_PATH;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_NUMBER;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_SIZE;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_SORT_DIRECTION;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_SORT_FIELD;

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

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.common.pagination.Page;
import es.fjtorres.cpFacturas.rest.api.ICustomerResource;
import es.fjtorres.cpFacturas.server.service.ICustomerService;

@Named
@Path(CUSTOMERS_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResourceImpl extends AbstractResource implements
      ICustomerResource {

   private final ICustomerService service;

   @Inject
   public CustomerResourceImpl(final ICustomerService pService) {
      this.service = pService;
   }

   public ICustomerService getService() {
      return service;
   }

   @Override
   @GET
   public Response find(
         @DefaultValue("0") @QueryParam(PAGE_NUMBER) final int page,
         @DefaultValue("10") @QueryParam(PAGE_SIZE) final int pageSize,
         @QueryParam(PAGE_SORT_FIELD) final String sortField,
         @DefaultValue("ASC") @QueryParam(PAGE_SORT_DIRECTION) final String sortDirection) {
      Page<CustomerDto> wrapper = null;
      try {
         wrapper = getService().find(page, pageSize, sortField, sortDirection);
      } catch (final IllegalArgumentException iae) {
         badRequest(iae.getMessage());
      }
      return Response.ok(wrapper).build();
   }

   @Override
   @Path("{id}")
   @GET
   public Response findById(@PathParam("id") final Long pId) {
      return Response.ok(getService().findById(pId)).build();
   }

   @Override
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response add(final CustomerDto pDto) {
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
   public Response update(final CustomerDto pDto) {
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

   @Override
   @GET
   @Path("{code}")
   public Response findByCode(final String code) {
      return null;
   }

}
