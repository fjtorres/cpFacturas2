package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import es.fjtorres.cpFacturas.common.CustomerType;
import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.i18n.Enums;
import es.fjtorres.cpFacturas.gwtClient.client.ui.widget.EnumListBox;

public class CustomerFormView extends ViewWithUiHandlers<CustomerFormUiHandlers> implements
        CustomerFormPresenter.MyView, Editor<CustomerDto> {

    interface Binder extends UiBinder<Widget, CustomerFormView> {
    }

    interface Driver extends SimpleBeanEditorDriver<CustomerDto, CustomerFormView> {

    }

    private static final CustomerType DEFAULT_TYPE = CustomerType.PERSON;

    @Path("type")
    @UiField(provided = true)
    protected EnumListBox<CustomerType> comboType;

    @Path("code")
    @UiField
    protected TextBox txtCode;

    @Path("firstName")
    @UiField
    protected TextBox txtFirstName;

    @Path("lastName")
    @UiField
    protected TextBox txtLastName;

    @Path("contactData.primaryPhoneNumber")
    @UiField
    protected TextBox txtPrimaryPhone;

    @Path("contactData.secundayPhoneNumber")
    @UiField
    protected TextBox txtSecondaryPhone;

    @Path("contactData.email")
    @UiField
    protected TextBox txtEmail;

    @Path("contactData.fax")
    @UiField
    protected TextBox txtFax;

    @Path("contactData.address")
    @UiField
    protected TextArea txtAddress;

    @UiField
    protected Form frmCustomer;

    @UiField
    protected FormGroup fgLastName;

    @UiField
    protected Button btnSave;

    @UiField
    protected Button btnClear;

    @UiField
    protected Button btnBack;

    private final Driver driver;

    @Inject
    public CustomerFormView(final Binder uiBinder, final Driver pDriver, Enums constants) {
        this.driver = pDriver;

        comboType = new EnumListBox<CustomerType>(CustomerType.class, constants, DEFAULT_TYPE);

        initWidget(uiBinder.createAndBindUi(this));

        driver.initialize(this);
        driver.edit(new CustomerDto());
    }

    @UiHandler("comboType")
    public void onChangeComboType(final ChangeEvent event) {
        if (CustomerType.COMPANY.equals(comboType.getSelectedValue())) {
            fgLastName.setVisible(false);
        } else {
            fgLastName.setVisible(true);
        }
    }

    @UiHandler("btnSave")
    public void onClickBtnSave(final ClickEvent event) {
        event.preventDefault();
        getUiHandlers().onSave(driver.flush());
    }

    @UiHandler("btnClear")
    public void onClickBtnClear(final ClickEvent event) {
        event.preventDefault();
        reset();
    }

    @UiHandler("btnBack")
    public void onClickBtnBack(final ClickEvent event) {
        event.preventDefault();
        getUiHandlers().onBack();
    }

    @Override
    public void edit(CustomerDto pDto) {
        driver.edit(pDto);
    }

    @Override
    public void reset() {
        frmCustomer.reset();
        comboType.setValue(DEFAULT_TYPE);
    }

}
