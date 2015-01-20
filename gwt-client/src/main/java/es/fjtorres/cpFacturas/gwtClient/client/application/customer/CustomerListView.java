package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;

import es.fjtorres.cpFacturas.common.CustomerType;
import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.application.customer.CustomerListPresenter.MyView;
import es.fjtorres.cpFacturas.gwtClient.client.i18n.Customers;
import es.fjtorres.cpFacturas.gwtClient.client.i18n.Enums;
import es.fjtorres.cpFacturas.gwtClient.client.view.AbstractListView;

public class CustomerListView extends AbstractListView<CustomerDto, CustomerListUiHandlers>
        implements MyView {

    interface Binder extends UiBinder<Widget, CustomerListView> {
    }

    @UiField
    protected Button btnAdd;

    @UiField
    protected Button btnEdit;

    @UiField
    protected Button btnDelete;

    private final Customers i18nCustomers;

    private final Enums i18nEnums;

    @Inject
    public CustomerListView(final Binder uiBinder, Customers pI18nCustomers, Enums pI18nEnums) {
        super(10);

        this.i18nCustomers = pI18nCustomers;
        this.i18nEnums = pI18nEnums;

        initWidget(uiBinder.createAndBindUi(this));

        initGrid();
    }

    @Override
    public void createGridColumns() {
        final TextColumn<CustomerDto> codeColumn = new TextColumn<CustomerDto>() {

            @Override
            public String getValue(final CustomerDto pObject) {
                String result = "";
                if (pObject != null) {
                    result = pObject.getCode();
                }
                return result;
            }
        };

        dataGrid.addColumn(codeColumn, i18nCustomers.field_code());
        dataGrid.setColumnWidth(codeColumn, 100, Unit.PX);

        final TextColumn<CustomerDto> typeColumn = new TextColumn<CustomerDto>() {

            @Override
            public String getValue(final CustomerDto pObject) {
                String result = "";
                if (pObject != null && pObject.getType() != null) {
                    switch (pObject.getType()) {
                    case COMPANY:
                        result = i18nEnums.es_fjtorres_cpFacturas_common_CustomerType_COMPANY();
                        break;
                    case PERSON:
                        result = i18nEnums.es_fjtorres_cpFacturas_common_CustomerType_PERSON();
                        break;
                    default:
                        break;
                    }
                }
                return result;
            }
        };

        dataGrid.addColumn(typeColumn, i18nCustomers.field_type());
        dataGrid.setColumnWidth(typeColumn, 100, Unit.PX);

        final TextColumn<CustomerDto> nameColumn = new TextColumn<CustomerDto>() {

            @Override
            public String getValue(final CustomerDto pObject) {
                String result = "";
                if (pObject != null) {
                    final StringBuilder sb = new StringBuilder();
                    if (CustomerType.PERSON.equals(pObject.getType())) {
                        sb.append(pObject.getLastName()).append(", ");
                    }
                    sb.append(pObject.getFirstName());
                    result = sb.toString();
                }
                return result;
            }
        };

        dataGrid.addColumn(nameColumn, i18nCustomers.field_firstName());
    }

    @UiHandler("btnAdd")
    void onClickBtnAdd(final ClickEvent event) {
        getUiHandlers().onAdd();
    }

    @UiHandler("btnEdit")
    void onClickBtnEdit(final ClickEvent event) {
        if (isActiveSelection()) {
            getUiHandlers().onEdit(getSelectedItem());
        } else {
            getUiHandlers().onSelectionError();
        }
    }

    @UiHandler("btnDelete")
    void onClickBtnDelete(final ClickEvent event) {
        if (isActiveSelection()) {
            getUiHandlers().onDelete(getSelectedItem());
        } else {
            getUiHandlers().onSelectionError();
        }
    }

}
