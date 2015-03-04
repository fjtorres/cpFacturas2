package it.es.fjtorres.cpFacturas.server.api.impl;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.Assert;
import org.testng.annotations.Test;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.rest.api.NameTokens;
import es.fjtorres.cpFacturas.server.config.ApplicationConfig;

public class CustomerResourceImplTest extends JerseyTestNg.ContainerPerClassTest {

    @Override
    protected Application configure() {
        return new ApplicationConfig().property("contextConfigLocation",
                "classpath:applicationContext-test.xml");
    }

    @Test
    public void addTest() {
        final Response response = target(NameTokens.CUSTOMERS_PATH).request()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .post(Entity.entity(new CustomerDto(), MediaType.APPLICATION_JSON));

        Assert.assertNotNull(response, "Response cannot be null");
        Assert.assertEquals(response.getStatus(), Status.OK.getStatusCode(),
                "Response status code isn't ok");
    }

}
