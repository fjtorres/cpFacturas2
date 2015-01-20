package es.fjtorres.cpFacturas.server.service;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.ValidationException;

public interface ICustomerService {

    /**
     * 
     * @param page
     * @param pageSize
     * @param sortField
     * @param sortDirection
     * @return
     * @throws IllegalArgumentException
     *             If any parameter are invalid.
     */
    CustomerPageDto find(int page, int pageSize, String sortField, String sortDirection)
            throws IllegalArgumentException;

    CustomerDto findById(Long id);

    void add(CustomerDto dto) throws ValidationException;

    void update(CustomerDto dto) throws ValidationException;

    void delete(Long id);
}
