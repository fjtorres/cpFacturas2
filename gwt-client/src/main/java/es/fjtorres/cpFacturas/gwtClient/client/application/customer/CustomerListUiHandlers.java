package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.view.ListUiHandlers;

public interface CustomerListUiHandlers extends ListUiHandlers {

    void onAdd();

    void onEdit(CustomerDto selectedItem);

    void onDelete(CustomerDto selectedItem);
    
    void onSelectionError();

}
