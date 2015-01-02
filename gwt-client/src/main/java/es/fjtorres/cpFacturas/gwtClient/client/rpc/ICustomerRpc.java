package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.AppException;

@RemoteServiceRelativePath("services/customer")
public interface ICustomerRpc extends RemoteService {

    CustomerPageDto find(int page, int pageSize);
    
    void save(CustomerDto dto) throws AppException;
}
