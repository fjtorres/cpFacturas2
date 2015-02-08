package es.fjtorres.cpFacturas.gwtClient.server.api.impl;

import static es.fjtorres.cpFacturas.rest.api.NameTokens.CUSTOMERS_PATH;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_NUMBER;
import static es.fjtorres.cpFacturas.rest.api.NameTokens.PAGE_SIZE;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.pagination.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.gwtClient.server.api.ICustomerClient;

public class CustomerApiClient extends AbstractApiClient implements ICustomerClient {

    public CustomerApiClient(final String pBaseUrl) {
        super(pBaseUrl);
    }

    @Override
    public CustomerPageDto find(final int pPage, final int pPageSize) {
        final WebTarget target = getTarget(CUSTOMERS_PATH).queryParam(PAGE_NUMBER, pPage)
                .queryParam(PAGE_SIZE, pPageSize);
        return target.request(DEFAULT_MEDIA).header(AUTH_HEADER, getAuthToken())
                .get(CustomerPageDto.class);
    }

    @Override
    public void save(final CustomerDto pDto) throws AppException {
        final WebTarget target = getTarget(CUSTOMERS_PATH);

        Response response = null;
        final Entity<CustomerDto> entity = parseEntity(pDto);
        if (pDto.getId() == null) {
            response = target.request().header(AUTH_HEADER, getAuthToken()).post(entity);
        } else {
            response = target.request().header(AUTH_HEADER, getAuthToken()).put(entity);
        }

        checkResponseStatus(response);
    }

    @Override
    public CustomerDto findByCode(final String code) throws AppException {
        return getTarget(CUSTOMERS_PATH).path(code).request(DEFAULT_MEDIA)
                .header(AUTH_HEADER, getAuthToken()).get(CustomerDto.class);
    }

    @Override
    public void delete(Long pId) throws AppException {
        final Response response = getTarget(CUSTOMERS_PATH).path(String.valueOf(pId))
                .request(DEFAULT_MEDIA).header(AUTH_HEADER, getAuthToken()).delete();
        checkResponseStatus(response);
    }
}
