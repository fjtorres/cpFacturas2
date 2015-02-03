package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import javax.inject.Singleton;

import es.fjtorres.cpFacturas.common.dto.pagination.InvoicePageDto;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.IInvoiceRpc;

@Singleton
public class InvoiceRpcImpl extends AbstractEntityRpc implements IInvoiceRpc {

   /**
    * 
    */
   private static final long serialVersionUID = 5689029655389034376L;

   @Override
   public InvoicePageDto find(final int page, final int pageSize) {
      // TODO Auto-generated method stub
      return null;
   }

}
