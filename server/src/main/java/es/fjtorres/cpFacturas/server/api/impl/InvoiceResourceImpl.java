package es.fjtorres.cpFacturas.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.INVOICES_PATH;
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

import es.fjtorres.cpFacturas.common.dto.InvoiceDto;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.common.pagination.Page;
import es.fjtorres.cpFacturas.rest.api.IInvoiceResource;
import es.fjtorres.cpFacturas.server.service.ExportResult;
import es.fjtorres.cpFacturas.server.service.IInvoiceService;

@Named
@Path(INVOICES_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceResourceImpl extends AbstractResource implements IInvoiceResource {

   /**
    * 
    */
   private static final long serialVersionUID = 3086471833764495390L;

   private final IInvoiceService service;

   @Inject
   public InvoiceResourceImpl(final IInvoiceService pService) {
      this.service = pService;
   }

   public IInvoiceService getService() {
      return service;
   }

   @Override
   @GET
   public Response find(@DefaultValue("0") @QueryParam(PAGE_NUMBER) final int page,
         @DefaultValue("10") @QueryParam(PAGE_SIZE) final int pageSize,
         @QueryParam(PAGE_SORT_FIELD) final String sortField,
         @DefaultValue("ASC") @QueryParam(PAGE_SORT_DIRECTION) final String sortDirection) {
      Page<InvoiceDto> wrapper = null;
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
   public Response add(final InvoiceDto pDto) {
      try {
         getService().add(pDto);
      } catch (final ValidationException ve) {
         badRequest(ve.getErrors());
      }

      return Response.ok().build();
   }

   @Override
   @PUT
   @Path("{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response update(final InvoiceDto pDto) {
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

   @Path("{id}")
   @GET
   @Override
   public Response findById(@PathParam("id") final Long pId) {
      return Response.ok(getService().findById(pId)).build();
   }

   @Path("export/{id}")
   @GET
   @Override
   public Response export(@PathParam("id") final Long pId) {
      final ExportResult result = getService().export(pId);
      return Response.ok(result.getContent())
            .header("Content-Disposition", "attachment; filename=" + result.getFilename()).type(result.getMediaType()).build();
   }
}
