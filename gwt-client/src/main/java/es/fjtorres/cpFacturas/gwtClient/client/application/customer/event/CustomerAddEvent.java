package es.fjtorres.cpFacturas.gwtClient.client.application.customer.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.event.CustomerAddEvent.CustomerAddHandler;

public class CustomerAddEvent extends GwtEvent<CustomerAddHandler> {

    public interface CustomerAddHandler extends EventHandler {
        void onAdd(CustomerAddEvent event);
    }

    private static final Type<CustomerAddHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source, CustomerDto pDto) {
        fire(source, pDto, false);
    }

    public static void fire(HasHandlers source, CustomerDto pDto, Boolean isNew) {
        source.fireEvent(new CustomerAddEvent(pDto, isNew));
    }

    public CustomerAddEvent(CustomerDto pDto, Boolean pIsNew) {
        dto = pDto;
        isNew = pIsNew;
    }

    private final CustomerDto dto;
    private final Boolean isNew;

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<CustomerAddHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CustomerAddHandler pHandler) {
        pHandler.onAdd(this);
    }

    public CustomerDto getDto() {
        return dto;
    }

    public Boolean getIsNew() {
        return isNew;
    }
}
