package es.fjtorres.cpFacturas.rest.api;

import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.common.dto.InvoiceDto;

public interface IInvoiceResource {
   Response find(int page, int pageSize, String sortField, String sortDirection);

   Response add(InvoiceDto dto);

   Response update(InvoiceDto dto);

   Response delete(Long id);
}
