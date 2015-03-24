package es.fjtorres.cpFacturas.server.service;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.pagination.CustomerPageDto;
import es.fjtorres.cpFacturas.server.model.Customer;

public interface ICustomerService extends IEntityService<Customer, CustomerDto, Long> {

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
   CustomerPageDto find(int page, int pageSize, String sortField, String sortDirection)
         throws IllegalArgumentException;

}
