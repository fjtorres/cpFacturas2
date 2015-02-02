package es.fjtorres.cpFacturas.common.dto.pagination;

import java.util.Collections;
import java.util.List;

import es.fjtorres.cpFacturas.common.dto.InsurerDto;
import es.fjtorres.cpFacturas.common.pagination.Page;

public class InsurerPageDto implements Page<InsurerDto> {

   /**
    * 
    */
   private static final long serialVersionUID = 814004198142908456L;

   private long total;

   private List<InsurerDto> list;

   public InsurerPageDto() {
      this.total = 0;
      this.list = Collections.emptyList();
   }

   /**
    * @param pTotal
    * @param pList
    */
   public InsurerPageDto(final long pTotal, final List<InsurerDto> pList) {
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
   public List<InsurerDto> getList() {
      return list;
   }

   @Override
   public void setList(final List<InsurerDto> list) {
      this.list = list;
   }
}
