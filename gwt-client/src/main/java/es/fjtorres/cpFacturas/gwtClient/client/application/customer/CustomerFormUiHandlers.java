package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import com.gwtplatform.mvp.client.UiHandlers;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;

public interface CustomerFormUiHandlers extends UiHandlers {

    void onSave(final CustomerDto dto);
    
    void onBack();
}
