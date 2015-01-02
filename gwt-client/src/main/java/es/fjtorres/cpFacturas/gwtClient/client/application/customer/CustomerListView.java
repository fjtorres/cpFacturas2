package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.CustomerListPresenter.MyView;
import es.fjtorres.cpFacturas.gwtClient.client.i18n.Customers;
import es.fjtorres.cpFacturas.gwtClient.client.view.AbstractListView;

public class CustomerListView extends AbstractListView<CustomerDto, CustomerListUiHandlers>
        implements MyView {

    interface Binder extends UiBinder<Widget, CustomerListView> {
    }

    @UiField
    protected Button btnAddCustomer;

    private final Customers i18nCustomers;

    @Inject
    public CustomerListView(final Binder uiBinder, Customers pI18nCustomers) {
        super(10);

        this.i18nCustomers = pI18nCustomers;

        initWidget(uiBinder.createAndBindUi(this));

        initGrid();
    }

    @Override
    public void createGridColumns() {
        final TextColumn<CustomerDto> codeColumn = new TextColumn<CustomerDto>() {

            @Override
            public String getValue(final CustomerDto pObject) {
                return pObject.getCode();
            }
        };

        dataGrid.addColumn(codeColumn, i18nCustomers.field_code());

        final TextColumn<CustomerDto> firstNameColumn = new TextColumn<CustomerDto>() {

            @Override
            public String getValue(final CustomerDto pObject) {
                return pObject.getFirstName();
            }
        };

        dataGrid.addColumn(firstNameColumn, i18nCustomers.field_firstName());

        final TextColumn<CustomerDto> lastNameColumn = new TextColumn<CustomerDto>() {

            @Override
            public String getValue(final CustomerDto pObject) {
                return pObject.getLastName();
            }
        };

        dataGrid.addColumn(lastNameColumn, i18nCustomers.field_lastName());
    }

    @UiHandler("btnAddCustomer")
    void onClickBtnAddCustomer(final ClickEvent event) {
        getUiHandlers().revealAddCustomer();
    }

}
