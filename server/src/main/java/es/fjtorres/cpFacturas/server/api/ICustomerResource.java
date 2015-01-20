package es.fjtorres.cpFacturas.server.api;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;

public interface ICustomerResource {

    Response find(int page, int pageSize, String sortField, String sortDirection);

    Response findById(@NotNull Long id);

    Response add(@NotNull CustomerDto dto);

    Response update(@NotNull CustomerDto dto);

    Response delete(@NotNull Long id);
}
