package es.fjtorres.cpFacturas.gwtClient.client.view;

import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import es.fjtorres.cpFacturas.common.dto.AbstractDto;
import es.fjtorres.cpFacturas.common.pagination.Page;

public abstract class AbstractListView<T extends AbstractDto<?>, H extends ListUiHandlers> extends
        ViewWithUiHandlers<H> implements ListView<T, H> {

    @UiField(provided = true)
    public DataGrid<T> dataGrid = new DataGrid<T>();

    @UiField
    public Pagination dataGridPagination;

    private SimplePager dataGridPager = new SimplePager();

    protected AsyncDataProvider<T> dataGridProvider;

    private boolean init = false;

    private final int pageSize;

    public AbstractListView(final int pPageSize) {
        this.pageSize = pPageSize;
    }

    protected void initGrid() {
        if (!init) {
            dataGrid.setPageSize(pageSize);
            createGridColumns();

            dataGrid.addRangeChangeHandler(new RangeChangeEvent.Handler() {

                @Override
                public void onRangeChange(RangeChangeEvent pEvent) {
                    rebuildPagination();
                }
            });

            dataGridPager.setDisplay(dataGrid);
            dataGridPagination.clear();

            rebuildPagination();

            init = true;
        }
    }

    public abstract void createGridColumns();

    @Override
    public void initDataProvider() {
        dataGridProvider = new AsyncDataProvider<T>() {
            @Override
            protected void onRangeChanged(HasData<T> display) {
                Range range = display.getVisibleRange();
                getUiHandlers().fetchData(range.getStart(), range.getLength());
            }
        };

        dataGridProvider.addDataDisplay(getDataGrid());
    }

    @Override
    public void displayData(int pOffset, final Page<T> page) {
        dataGridProvider.updateRowData(pOffset, page.getList());
        dataGridProvider.updateRowCount(page.getTotal(), true);
        rebuildPagination();
    }
    
    protected void rebuildPagination() {
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

    public void setDataGridProvider(AsyncDataProvider<T> pDataGridProvider) {
        dataGridProvider = pDataGridProvider;
    }

    public int getPageSize() {
        return pageSize;
    }

}
