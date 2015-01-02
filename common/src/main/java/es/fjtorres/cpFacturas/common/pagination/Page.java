package es.fjtorres.cpFacturas.common.pagination;

import java.io.Serializable;
import java.util.List;

import es.fjtorres.cpFacturas.common.dto.AbstractDto;

public interface Page<T extends AbstractDto<?>> extends Serializable {

    int getTotal();

    void setTotal(int total);

    List<T> getList();

    void setList(List<T> pList);
}
