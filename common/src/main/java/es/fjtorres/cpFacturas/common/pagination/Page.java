package es.fjtorres.cpFacturas.common.pagination;

import java.io.Serializable;
import java.util.List;

import es.fjtorres.cpFacturas.common.dto.AbstractDto;

public interface Page<T extends AbstractDto<?>> extends Serializable {

    long getTotal();

    void setTotal(long total);

    List<T> getList();

    void setList(List<T> pList);
}
