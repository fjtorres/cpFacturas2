package es.fjtorres.cpFacturas.gwtClient.client.view;

import java.io.Serializable;

import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.gwtplatform.mvp.client.ViewImpl;

public abstract class AbstractListView<T extends Serializable> extends ViewImpl {

    @UiField(provided = true)
    public DataGrid<T> dataGrid = new DataGrid<T>();

    @UiField
    public Pagination dataGridPagination;

    private SimplePager dataGridPager = new SimplePager();

    private AbstractDataProvider<T> dataGridProvider;

    private boolean init = false;

    private final int pageSize;

    public AbstractListView(final int pPageSize) {
        this.pageSize = pPageSize;
    }

    protected void initGrid() {
        if (!init) {
            dataGrid.setPageSize(pageSize);
            dataGridProvider = createProvider();
            createGridColumns();

            dataGrid.addRangeChangeHandler(new RangeChangeEvent.Handler() {

                @Override
                public void onRangeChange(RangeChangeEvent pEvent) {
                    rebuildPagination();
                }
            });

            dataGridPager.setDisplay(dataGrid);
            dataGridPagination.clear();
            dataGridProvider.addDataDisplay(dataGrid);
            rebuildPagination();

            init = true;
        }
    }

    public abstract void createGridColumns();

    public abstract <P extends AbstractDataProvider<T>> P createProvider();

    protected void rebuildPagination () {
        getDataGridPagination().rebuild(getDataGridPager());
    }
    
    public DataGrid<T> getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(DataGrid<T> pDataGrid) {
        dataGrid = pDataGrid;
    }

    public Pagination getDataGridPagination() {
        return dataGridPagination;
    }

    public void setDataGridPagination(Pagination pDataGridPagination) {
        dataGridPagination = pDataGridPagination;
    }

    public SimplePager getDataGridPager() {
        return dataGridPager;
    }

    public void setDataGridPager(SimplePager pDataGridPager) {
        dataGridPager = pDataGridPager;
    }

    public AbstractDataProvider<T> getDataGridProvider() {
        return dataGridProvider;
    }

    public void setDataGridProvider(AbstractDataProvider<T> pDataGridProvider) {
        dataGridProvider = pDataGridProvider;
    }

    public int getPageSize() {
        return pageSize;
    }

}
