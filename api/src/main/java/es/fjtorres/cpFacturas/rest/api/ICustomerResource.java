package es.fjtorres.cpFacturas.rest.api;

import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;

public interface ICustomerResource {

   Response find(int page, int pageSize, String sortField, String sortDirection);

   Response add(CustomerDto dto);

   Response update(CustomerDto dto);

   Response delete(Long id);

   Response findById(Long id);
   
   Response findByText(String searchText);
}
