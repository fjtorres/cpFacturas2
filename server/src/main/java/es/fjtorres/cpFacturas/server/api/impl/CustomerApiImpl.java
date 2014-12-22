package es.fjtorres.cpFacturas.server.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.pagination.Page;
import es.fjtorres.cpFacturas.server.api.ICustomerApi;
import es.fjtorres.cpFacturas.server.model.Customer;
import es.fjtorres.cpFacturas.server.service.ICustomerService;

@Named
@Path("/api/customers")
public class CustomerApiImpl implements ICustomerApi {

    private final ICustomerService service;

    @Inject
    public CustomerApiImpl(final ICustomerService pService) {
        this.service = pService;
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Page<CustomerDto> findCustomers(final int pPage, final int pPageSize) {

        final List<Customer> entities = getService().getCustomers();
        List<CustomerDto> dtos = Collections.emptyList();
        if (entities != null && !entities.isEmpty()) {
            dtos = new ArrayList<CustomerDto>(entities.size());
            for (final Customer entity : entities) {
                final CustomerDto dto = new CustomerDto();
                dto.setId(entity.getId());
                dto.setCode(entity.getCode());
                dto.setFirstName(entity.getFirstName());
                dto.setLastName(entity.getLastName());
                dto.setVersion(entity.getVersion());
                dtos.add(dto);
            }
        }
        
        Page<CustomerDto> page = new Page<CustomerDto>();
        page
        return page;
    }

    public ICustomerService getService() {
        return service;
    }

}
