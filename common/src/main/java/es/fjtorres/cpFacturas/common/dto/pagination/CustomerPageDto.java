package es.fjtorres.cpFacturas.common.dto.pagination;

import java.util.Collections;
import java.util.List;

import es.fjtorres.cpFacturas.common.dto.CustomerDto;
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
   public CustomerPageDto(final long pTotal, final List<CustomerDto> pList) {
      total = pTotal;
      list = pList;
   }

   @Override
   public long getTotal() {
      return total;
   }

   @Override
   public void setTotal(final long pTotal) {
      total = pTotal;
   }

   @Override
   public List<CustomerDto> getList() {
      return list;
   }

   @Override
   public void setList(final List<CustomerDto> pList) {
      list = pList;
   }

}
