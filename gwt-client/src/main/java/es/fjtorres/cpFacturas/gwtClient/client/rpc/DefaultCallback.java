package es.fjtorres.cpFacturas.gwtClient.client.rpc;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DefaultCallback<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(final Throwable pCaught) {
        Window.alert("Error");
    }

    @Override
    public void onSuccess(T pResult) {
        
    }

}
