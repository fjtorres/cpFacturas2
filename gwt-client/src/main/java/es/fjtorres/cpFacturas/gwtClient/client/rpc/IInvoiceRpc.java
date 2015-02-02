package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.fjtorres.cpFacturas.common.dto.pagination.InvoicePageDto;

@RemoteServiceRelativePath("services/invoice")
public interface IInvoiceRpc extends RemoteService {
   InvoicePageDto find(int page, int pageSize);
}
