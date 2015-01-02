package es.fjtorres.cpFacturas.gwtClient.client.view;

import com.google.gwt.view.client.HasData;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.View;

import es.fjtorres.cpFacturas.common.dto.AbstractDto;
import es.fjtorres.cpFacturas.common.pagination.Page;

public interface ListView<T extends AbstractDto<?>, H extends UiHandlers> extends View,
        HasUiHandlers<H> {
    void initDataProvider();

    void displayData(int offset, Page<T> page);

    HasData<T> getDataGrid();
}
