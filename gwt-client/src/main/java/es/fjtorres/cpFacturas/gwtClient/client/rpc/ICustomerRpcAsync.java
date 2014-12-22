package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;

public interface ICustomerRpcAsync {

    void findCustomers(int page, int pageSize, AsyncCallback<List<CustomerDto>> callback);

}
