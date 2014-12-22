package es.fjtorres.cpFacturas.common.pagination;

import java.io.Serializable;
import java.util.List;

import es.fjtorres.cpFacturas.common.dto.AbstractDto;

public class Page<T extends AbstractDto> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8970644777517722234L;
    
    private long total;
    
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long pTotal) {
        total = pTotal;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> pList) {
        list = pList;
    }
}
