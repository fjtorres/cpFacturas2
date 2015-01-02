package es.fjtorres.cpFacturas.server.service;

import javax.validation.constraints.NotNull;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;

public interface ICustomerService {

    CustomerPageDto find(int page, int pageSize, String sortField, String sortDirection);

    CustomerDto find(@NotNull Long id);

    void add(@NotNull CustomerDto dto);

    void update(@NotNull CustomerDto dto);

    void delete(@NotNull Long id);
}
