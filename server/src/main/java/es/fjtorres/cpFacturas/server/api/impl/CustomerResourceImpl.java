package es.fjtorres.cpFacturas.server.api.impl;

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
import es.fjtorres.cpFacturas.server.api.ICustomerResource;
import es.fjtorres.cpFacturas.server.service.ICustomerService;

@Named
@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResourceImpl extends AbstractResource implements ICustomerResource {

    private final ICustomerService service;

    @Inject
    public CustomerResourceImpl(final ICustomerService pService) {
        this.service = pService;
    }

    @Override
    @GET
    public Response find(@DefaultValue("0") @QueryParam("page") int page,
            @DefaultValue("10") @QueryParam("pageSize") int pageSize,
            @QueryParam("sortField") String sortField,
            @DefaultValue("ASC") @QueryParam("sortDirection") String sortDirection) {

        return Response.ok(getService().find(page, pageSize, sortField, sortDirection)).build();
    }

    @Override
    @Path("{id}")
    @GET
    public Response find(@PathParam("id")
    final Long pId) {
        return Response.ok(getService().find(pId)).build();
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(final CustomerDto pDto) {
        getService().add(pDto);
        return Response.ok().build();
    }

    @Override
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(final CustomerDto pDto) {
        getService().update(pDto);
        return Response.ok().build();
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id")
    final Long pId) {
        getService().delete(pId);
        return Response.ok().build();
    }

    public ICustomerService getService() {
        return service;
    }

}
