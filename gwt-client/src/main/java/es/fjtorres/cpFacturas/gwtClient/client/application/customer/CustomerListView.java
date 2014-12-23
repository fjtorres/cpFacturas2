package es.fjtorres.cpFacturas.gwtClient.client.application.customer;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.DefaultCallback;
import es.fjtorres.cpFacturas.gwtClient.client.rpc.ICustomerRpcAsync;
import es.fjtorres.cpFacturas.gwtClient.client.view.AbstractListView;

public class CustomerListView extends AbstractListView<CustomerDto> implements
      CustomerListPresenter.MyView {

   interface Binder extends UiBinder<Widget, CustomerListView> {
   }

   private final ICustomerRpcAsync rpc;

   @Inject
   public CustomerListView(final Binder uiBinder, final ICustomerRpcAsync pRpc) {
      super(10);

      this.rpc = pRpc;

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

      dataGrid.addColumn(codeColumn, "Code");

      final TextColumn<CustomerDto> firstNameColumn = new TextColumn<CustomerDto>() {

         @Override
         public String getValue(final CustomerDto pObject) {
            return pObject.getFirstName();
         }
      };

      dataGrid.addColumn(firstNameColumn, "First name");

      final TextColumn<CustomerDto> lastNameColumn = new TextColumn<CustomerDto>() {

         @Override
         public String getValue(final CustomerDto pObject) {
            return pObject.getLastName();
         }
      };

      dataGrid.addColumn(lastNameColumn, "Last name");
   }

   @Override
   @SuppressWarnings("unchecked")
   public AsyncDataProvider<CustomerDto> createProvider() {
      return new AsyncDataProvider<CustomerDto>() {

         @Override
         protected void onRangeChanged(final HasData<CustomerDto> pDisplay) {
            final int start = pDisplay.getVisibleRange().getStart();

            rpc.findCustomers(start / getPageSize(), getPageSize(),
                  new DefaultCallback<List<CustomerDto>>() {

                     @Override
                     public void onSuccess(final List<CustomerDto> pResult) {
                        int end = start
                              + pDisplay.getVisibleRange().getLength();

                        end = end >= pResult.size() ? pResult.size() : end;

                        updateRowData(start, pResult.subList(start, end));
                        updateRowCount(pResult.size(), true);

                        rebuildPagination();
                     }

                  });

         }

      };
   }

}
