package es.fjtorres.cpFacturas.common.dto.pagination;

import java.util.Collections;
import java.util.List;

import es.fjtorres.cpFacturas.common.dto.InvoiceDto;
import es.fjtorres.cpFacturas.common.pagination.Page;

public class InvoicePageDto implements Page<InvoiceDto> {

   /**
    * 
    */
   private static final long serialVersionUID = -145640738228902051L;

   private long total;

   private List<InvoiceDto> list;

   public InvoicePageDto() {
      this.total = 0;
      this.list = Collections.emptyList();
   }

   /**
    * @param pTotal
    * @param pList
    */
   public InvoicePageDto(final long pTotal, final List<InvoiceDto> pList) {
      total = pTotal;
      list = pList;
   }

   @Override
   public long getTotal() {
      return total;
   }

   @Override
   public void setTotal(final long total) {
      this.total = total;
   }

   @Override
   public List<InvoiceDto> getList() {
      return list;
   }

   @Override
   public void setList(final List<InvoiceDto> list) {
      this.list = list;
   }
}
