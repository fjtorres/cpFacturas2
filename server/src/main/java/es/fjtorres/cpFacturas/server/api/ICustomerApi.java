package es.fjtorres.cpFacturas.server.api;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.pagination.Page;

public interface ICustomerApi {

    Page<CustomerDto> findCustomers(int page, int pageSize);
}
