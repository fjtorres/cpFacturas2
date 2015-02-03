package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.fjtorres.cpFacturas.common.dto.pagination.InsurerPageDto;

@RemoteServiceRelativePath("services/insurer")
public interface IInsurerRpc extends RemoteService {
   InsurerPageDto find(int page, int pageSize);
}
