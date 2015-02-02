package es.fjtorres.cpFacturas.server.service;

import es.fjtorres.cpFacturas.common.dto.InvoiceDto;
import es.fjtorres.cpFacturas.common.dto.pagination.InvoicePageDto;
import es.fjtorres.cpFacturas.server.model.Invoice;

public interface IInvoiceService extends
      IEntityService<Invoice, InvoiceDto, Long> {

   /**
    * 
    * @param page
    * @param pageSize
    * @param sortField
    * @param sortDirection
    * @return
    * @throws IllegalArgumentException
    *            If any parameter are invalid.
    */
   InvoicePageDto find(int page, int pageSize, String sortField,
         String sortDirection) throws IllegalArgumentException;

}
