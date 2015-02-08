package es.fjtorres.cpFacturas.gwtClient.server.api.impl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.message.internal.FormMultivaluedMapProvider;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import es.fjtorres.cpFacturas.common.exception.AppException;
import es.fjtorres.cpFacturas.common.exception.ValidationException;
import es.fjtorres.cpFacturas.gwtClient.server.api.ISecuredClient;

public abstract class AbstractApiClient implements ISecuredClient {

    protected static final String DEFAULT_MEDIA = MediaType.APPLICATION_JSON;

    private final String baseUrl;

    private String authToken;

    public AbstractApiClient(final String pBaseUrl) {
        this.baseUrl = pBaseUrl;
    }

    protected Client getClient() {
        return ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class, FormMultivaluedMapProvider.class));
    }

    protected WebTarget getTarget(final String pPath) {
        String auxBaseUrl = baseUrl;
        if (auxBaseUrl.endsWith("/") && pPath.startsWith("/")) {
            auxBaseUrl = auxBaseUrl.substring(0, auxBaseUrl.length() - 1);
        }

        return getClient().target(auxBaseUrl).path(pPath);
    }

    protected <T> Entity<T> parseEntity(final T pEntity) {
        return parseEntity(pEntity, DEFAULT_MEDIA);
    }

    protected <T> Entity<T> parseEntity(final T pEntity, final String pMediaType) {
        return Entity.entity(pEntity, pMediaType);
    }

    protected <E> E readEntity(final Response pResponse, final Class<E> pEntityClass)
            throws AppException {
        try {
            return pResponse.readEntity(pEntityClass);
        } catch (final ProcessingException pe) {
            throw new AppException("Exception when read server response.");
        }
    }

    protected void checkResponseStatus(final Response pResponse) throws AppException {
        if (pResponse != null) {
            if (Response.Status.OK.getStatusCode() != pResponse.getStatus()) {
                switch (pResponse.getStatus()) {
                case 400:
                    badRequestStatus(pResponse);
                    break;
                default:
                    throw new AppException("Exception when call REST api.");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void badRequestStatus(final Response pResponse) throws ValidationException,
            AppException {
        final String strError = "errors";
        final Map<String, Object> errorResponse = readEntity(pResponse, Map.class);
        if (errorResponse.containsKey(strError)) {
            throw new ValidationException((List<String>) errorResponse.get(strError));
        }
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String pAuthToken) {
        authToken = pAuthToken;
    }
}
