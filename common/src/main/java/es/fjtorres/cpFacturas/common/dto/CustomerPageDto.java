package es.fjtorres.cpFacturas.common.dto;

import java.util.Collections;
import java.util.List;

import es.fjtorres.cpFacturas.common.pagination.Page;

public class CustomerPageDto implements Page<CustomerDto> {

    private static final long serialVersionUID = -673352284354903958L;

    private long total;

    private List<CustomerDto> list;

    public CustomerPageDto() {
        this.total = 0;
        this.list = Collections.emptyList();
    }

    /**
     * @param pTotal
     * @param pList
     */
    public CustomerPageDto(long pTotal, List<CustomerDto> pList) {
        super();
        total = pTotal;
        list = pList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long pTotal) {
        total = pTotal;
    }

    public List<CustomerDto> getList() {
        return list;
    }

    public void setList(List<CustomerDto> pList) {
        list = pList;
    }

}
