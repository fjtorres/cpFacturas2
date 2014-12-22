package es.fjtorres.cpFacturas.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import es.fjtorres.cpFacturas.server.model.Customer;
import es.fjtorres.cpFacturas.server.service.ICustomerService;

@Named
public class CustomerServiceImpl implements ICustomerService {

    private static List<Customer> MOCK_DATA;

    static {
        MOCK_DATA = new ArrayList<Customer>();

        for (int i = 0; i < 25; i++) {
            Customer dto = new Customer();
            dto.setId(new Long(i));
            dto.setCode("Code: " + i);
            dto.setFirstName("First name: " + i);
            dto.setLastName("Last name: " + i);
            MOCK_DATA.add(dto);
        }
    }
    
    public CustomerServiceImpl () {
        
    }
    
    @Override
    public List<Customer> getCustomers() {
        return MOCK_DATA;
    }

}
