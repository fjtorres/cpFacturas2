package es.fjtorres.cpFacturas.gwtClient.client.view;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ListUiHandlers extends UiHandlers {
    void fetchData(int start, int size);
}
