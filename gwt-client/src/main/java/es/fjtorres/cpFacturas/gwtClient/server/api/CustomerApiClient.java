package es.fjtorres.cpFacturas.gwtClient.server.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.common.dto.CustomerPageDto;
import es.fjtorres.cpFacturas.common.exception.AppException;

public class CustomerApiClient {

    public CustomerPageDto find(final int pPage, final int pPageSize) {
        final WebTarget target = getTarget("/customers").queryParam("page", pPage).queryParam(
                "pageSize", pPageSize);
        return target.request(MediaType.APPLICATION_JSON).get(CustomerPageDto.class);
    }

    public void save(final CustomerDto pDto) throws AppException {
        final WebTarget target = getTarget("/customers");

        Response response = null;
        if (pDto.getId() == null) {
            response = target.request().post(Entity.entity(pDto, MediaType.APPLICATION_JSON));
        } else {
            response = target.request().put(Entity.entity(pDto, MediaType.APPLICATION_JSON));
        }

        if (response != null) {
            if (Response.Status.OK.getStatusCode() != response.getStatus()) {
                switch (response.getStatus()) {
                case 400:
                    System.out.println(response.getEntity());
                    break;
                default:
                    throw new AppException("Exception when call REST api.");
                }
            }
        }
    }

    private Client getClient() {
        return ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
    }

    private WebTarget getTarget(final String pPath) {
        final WebTarget target = getClient().target("http://localhost:8080/server/api").path(pPath);
        return target;
    }
}
