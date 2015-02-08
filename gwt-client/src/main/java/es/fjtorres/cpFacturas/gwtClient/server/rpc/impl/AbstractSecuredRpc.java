package es.fjtorres.cpFacturas.gwtClient.server.rpc.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import es.fjtorres.cpFacturas.gwtClient.client.rpc.ISecuredRpc;

public abstract class AbstractSecuredRpc extends RemoteServiceServlet implements ISecuredRpc {

    /**
     * 
     */
    private static final long serialVersionUID = -6140675976888588333L;

    protected String getAuthToken() {
        return getCookieValue(AUTH_TOKEN);
    }

    protected String getCookieValue(final String cookieName) {
        String value = null;
        final Cookie[] cookies = getThreadLocalRequest().getCookies();
        if (cookieName != null && cookies != null && cookies.length != 0) {
            for (final Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    value = cookie.getValue();
                }
            }
        }
        
        if (value != null) {
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // FIXME ADD LOGGER
            }
        }
        
        return value;
        
    }

}
