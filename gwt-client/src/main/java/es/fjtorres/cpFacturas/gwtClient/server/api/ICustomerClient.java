package es.fjtorres.cpFacturas.gwtClient.server.api;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.common.exception.EntityNotFoundException;

public interface ICustomerClient {

   void save(final CustomerDto pDto) throws AppException;

   CustomerPageDto find(final int pPage, final int pPageSize);

   CustomerDto findByCode(final String code) throws EntityNotFoundException;

}
