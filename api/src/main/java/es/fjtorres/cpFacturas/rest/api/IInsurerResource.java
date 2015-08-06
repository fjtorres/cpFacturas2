package es.fjtorres.cpFacturas.rest.api;

import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.common.dto.InsurerDto;

public interface IInsurerResource {
   Response find(int page, int pageSize, String sortField, String sortDirection);

   Response add(InsurerDto dto);

   Response update(InsurerDto dto);

   Response delete(Long id);
   
   Response findById(Long id);
   
   Response findByText(String searchText);
}
