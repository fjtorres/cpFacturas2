package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import javax.inject.Singleton;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.pagination.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.common.exception.EntityNotFoundException;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.ICustomerRpc;
import es.fjtorres.cpFacturas.gwtClient.server.api.ICustomerClient;
import es.fjtorres.cpFacturas.gwtClient.server.api.impl.CustomerApiClient;

@Singleton
public class CustomerRpcImpl extends AbstractEntityRpc implements ICustomerRpc {

   private static final long serialVersionUID = -8827064477531767934L;

   private ICustomerClient client = new CustomerApiClient(
         "http://localhost:8080/server");

   @Override
   public CustomerPageDto find(final int page, final int pageSize) {
      return client.find(page, pageSize);
   }

   @Override
   public void save(final CustomerDto pDto) throws AppException {
      client.save(pDto);
   }

   @Override
   public CustomerDto findByCode(final String pCode)
         throws EntityNotFoundException {
      return client.findByCode(pCode);
   }

}
