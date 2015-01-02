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

    @UiField
    protected Form frmCustomer;

    @UiField(provided = true)
    protected EnumListBox<CustomerType> comboType;

    @UiField
    protected TextBox code;

    @UiField
    protected TextBox firstName;

    @UiField
    protected TextBox lastName;

    @Path("contactData.primaryPhoneNumber")
    @UiField
    protected TextBox primaryPhone;

    @Path("contactData.secundayPhoneNumber")
    @UiField
    protected TextBox secondaryPhone;

    @Path("contactData.email")
    @UiField
    protected TextBox email;

    @Path("contactData.fax")
    @UiField
    protected TextBox fax;

    @Path("contactData.address")
    @UiField
    protected TextArea address;

    @Ignore
    @UiField
    protected FormGroup fgLastName;

    @Ignore
    @UiField
    protected Button btnSave;

    @Ignore
    @UiField
    protected Button btnClear;

    @Ignore
    @UiField
    protected Button btnBack;

    private final Driver driver;

    @Inject
    public CustomerFormView(final Binder uiBinder, final Driver pDriver, Enums constants) {
        this.driver = pDriver;

        comboType = new EnumListBox<CustomerType>(CustomerType.class, constants);
        comboType.setValue(CustomerType.PERSON);

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
        frmCustomer.reset();
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

}
