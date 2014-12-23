package es.fjtorres.cpFacturas.server.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.pagination.Page;
import es.fjtorres.cpFacturas.common.pagination.PageFilter;
import es.fjtorres.cpFacturas.server.dozer.service.IDozerService;
import es.fjtorres.cpFacturas.server.model.Customer;
import es.fjtorres.cpFacturas.server.service.ICustomerService;

@Named
@Path("/api/customers")
public class CustomerServiceImpl implements ICustomerService {

   private static List<Customer> MOCK_DATA;

   static {
      MOCK_DATA = new ArrayList<Customer>();

      for (int i = 0; i < 25; i++) {
         Customer element = new Customer();
         element.setId(new Long(i));
         element.setCode("Code: " + i);
         element.setFirstName("First name: " + i);
         element.setLastName("Last name: " + i);
         MOCK_DATA.add(element);
      }
   }

   private final IDozerService dozerService;

   @Inject
   public CustomerServiceImpl(final IDozerService pDozerService) {
      this.dozerService = pDozerService;
   }

   @Override
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Page<CustomerDto> findCustomers(final PageFilter pFilter) {

      if (pFilter.getPageSize() == 0) {
         badRequest("page size can not be zero");
      }

      List<CustomerDto> dtos = Collections.emptyList();

      final Long total = countCustomers();

      if (total > 0) {

         int maxPages = (int) (total / pFilter.getPageSize());

         if (pFilter.getPage() > maxPages) {
            badRequest("the page can not be greater than: {0}", maxPages);
         }

         final List<Customer> entities = MOCK_DATA;

         dtos = getDozerService().convert(entities, CustomerDto.class);
      }

      final Page<CustomerDto> page = new Page<CustomerDto>();
      page.setList(dtos);
      page.setTotal(dtos.size());
      return page;
   }

   private Long countCustomers() {
      return new Long(MOCK_DATA.size());
   }

   private void badRequest(final String message, final Object... parameters) {
      if (message == null || message.trim().isEmpty()) {
         throw new BadRequestException();
      } else {
         badRequest(MessageFormat.format(message, parameters));
      }
   }

   private void badRequest(final String... messages) {
      if (messages == null || messages.length == 0) {
         throw new BadRequestException();
      } else {
         final Map<String, String[]> error = new HashMap<>();
         error.put("errors", messages);
         throw new BadRequestException(Response.status(Status.BAD_REQUEST)
               .entity(error).build());
      }
   }

   public IDozerService getDozerService() {
      return dozerService;
   }

}
