package es.fjtorres.cpFacturas.server.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.pagination.Page;
import es.fjtorres.cpFacturas.common.pagination.PageFilter;

public interface ICustomerService {
   Page<CustomerDto> findCustomers(@NotNull @Valid PageFilter filter);
}
