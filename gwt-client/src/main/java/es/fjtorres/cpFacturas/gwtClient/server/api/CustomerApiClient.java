package es.fjtorres.cpFacturas.gwtClient.server.api;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;

public class CustomerApiClient {

    private static final GenericType<List<CustomerDto>> LIST_TYPE = new GenericType<List<CustomerDto>>() {
    };

    public List<CustomerDto> findCustomers(final int pPage, final int pPageSize) {
        Client client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
        WebTarget target = client.target("http://localhost:8080/server/api").path("/customers")
                .queryParam("page", pPage).queryParam("pageSize", pPageSize);
        return target.request(MediaType.APPLICATION_JSON).get(LIST_TYPE);
    }
}
