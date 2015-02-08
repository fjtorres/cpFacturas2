package es.fjtorres.cpFacturas.gwtClient.server.api;

public interface ISecuredClient {

    String AUTH_HEADER = "X-Auth-Token";

    void setAuthToken(String authToken);
}
