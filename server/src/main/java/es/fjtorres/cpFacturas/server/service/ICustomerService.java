package es.fjtorres.cpFacturas.server.service;

import java.util.List;

import es.fjtorres.cpFacturas.server.model.Customer;

public interface ICustomerService {
    List<Customer> getCustomers();
}
