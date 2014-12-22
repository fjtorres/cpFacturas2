package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import java.util.List;

import javax.inject.Singleton;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.ICustomerRpc;
import es.fjtorres.cpFacturas.gwtClient.server.api.CustomerApiClient;

@Singleton
public class CustomerRpcImpl extends RemoteServiceServlet implements ICustomerRpc {

    /**
     * 
     */
    private static final long serialVersionUID = -8827064477531767934L;

    private CustomerApiClient client = new CustomerApiClient();

    @Override
    public List<CustomerDto> findCustomers(final int page, final int pageSize) {
        return client.findCustomers(page, pageSize);
    }

}
