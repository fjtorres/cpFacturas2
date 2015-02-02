package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import javax.inject.Singleton;

import es.fjtorres.cpFacturas.common.dto.pagination.InsurerPageDto;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.IInsurerRpc;

@Singleton
public class InsurerRpcImpl extends AbstractEntityRpc implements IInsurerRpc {

   /**
    * 
    */
   private static final long serialVersionUID = 633011217203226911L;

   @Override
   public InsurerPageDto find(final int page, final int pageSize) {
      // TODO Auto-generated method stub
      return null;
   }

}
