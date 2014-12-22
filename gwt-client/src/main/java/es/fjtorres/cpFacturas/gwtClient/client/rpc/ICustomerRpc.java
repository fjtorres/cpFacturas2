package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;

@RemoteServiceRelativePath("services/customer")
public interface ICustomerRpc extends RemoteService {

    List<CustomerDto> findCustomers(int page, int pageSize);
}
