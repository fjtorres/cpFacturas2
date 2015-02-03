package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import javax.inject.Singleton;

import es.fjtorres.cpFacturas.common.dto.pagination.InvoicePageDto;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.IVehicleRpc;

@Singleton
public class VehicleRpcImpl extends AbstractEntityRpc implements IVehicleRpc {

   /**
    * 
    */
   private static final long serialVersionUID = -7106032338867914645L;

   @Override
   public InvoicePageDto find(final int page, final int pageSize) {
      // TODO Auto-generated method stub
      return null;
   }

}
